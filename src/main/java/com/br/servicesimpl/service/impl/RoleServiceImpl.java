package com.br.servicesimpl.service.impl;

import com.br.domain.entity.Role;
import com.br.domain.entity.Membership;
import com.br.domain.entity.RoleMembership;
import com.br.servicesimpl.exceptions.BusinessException;
import com.br.servicesimpl.repository.RoleRepository;
import com.br.servicesimpl.repository.MembershipRepository;
import com.br.servicesimpl.repository.RoleMembershipRepository;
import com.br.servicesimpl.repository.TeamRepository;
import com.br.servicesimpl.repository.UserRepository;
import com.br.servicesimpl.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final RoleMembershipRepository roleMembershipRepository;

    private final TeamRepository teamRepository;

    private final MembershipRepository membershipRepository;


    private static final Logger LOGGER = Logger.getLogger(RoleServiceImpl.class.getName());

    static {
        LOGGER.setLevel(Level.INFO);
    }
    public RoleServiceImpl(RoleRepository roleRepository,
                           RoleMembershipRepository roleMembershipRepository,
                           TeamRepository teamRepository,
                           UserRepository userRepository,
                           MembershipRepository membershipRepository) {
        this.roleRepository = roleRepository;
        this.roleMembershipRepository = roleMembershipRepository;
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
    }

    @Override
    public Role createRole(Role role) {
        try {
            if(role.getName().equals("Developer"))
                role.setDefaultRole(true);
            LOGGER.info("Creating Role: " + role);
            return roleRepository.save(role);
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Role with name '" + role.getName() + "' already exists");
        }
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }

    @Override
    public Role updateRole(Role role) {
        role.setDefaultRole(false);
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = getRoleById(id);
        if (role.isDefaultRole()) {
            throw new BusinessException("The default role cannot be deleted.");
        }
        roleRepository.deleteById(id);
    }

    @Override
    public void assignRoleToMember(Long memberId, Long teamId, Long roleId) throws ClassNotFoundException {
        Membership membership = membershipRepository.findByTeamIdAndUserId(teamId, memberId)
                .orElseThrow(() -> new ClassNotFoundException("Relation between User and Team not found"));
        log.info("Membership found: " + membership);
        log.info("Creating RoleMembership ");

        RoleMembership roleMembership = criarMembership(membership, roleId);

        roleMembership.setUser(userRepository.findById(membership.getId().getUserId())
                .orElseThrow(() -> new ClassNotFoundException("Team not found")));

        roleMembership.setTeam(teamRepository.findById(membership.getId().getTeamId())
                .orElseThrow(() -> new ClassNotFoundException("Team not found")));
        roleMembership = roleMembershipRepository.save(roleMembership);
        log.info("RoleMembership created " + roleMembership);


    }

    private RoleMembership criarMembership(Membership membership, Long roleId) {
        return RoleMembership.builder()
                .user(membership.getUser())
                .team(membership.getTeam())
                .role(roleRepository.findById(roleId)
                        .orElseThrow(() -> new RuntimeException("Role with id " + roleId + " not found"))
                )
                .build();
    }




    @Override
    public Role getRoleByMembership(Long userId, Long teamId) {
        RoleMembership roleMembership = roleMembershipRepository.findByUserIdAndTeamId(userId, teamId)
                .orElseThrow(() -> new EntityNotFoundException("Membership not found"));

        return roleMembership.getRole();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findAllByMembershipsUserId(Long userId) {
        List<Role> roles = new ArrayList<>();
        List<RoleMembership> memberships = roleMembershipRepository.findAllByUserId(userId);
        for (RoleMembership membership : memberships) {
            roles.add(membership.getRole());
        }
        return roles;
    }



    @Override
    public List<Role> findAllByMembershipsTeamId(Long teamId) {
        return roleRepository.findAllByMembershipsTeamId(teamId);
    }


    @Override
    public List<RoleMembership> getMembershipsByRoleId(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        List<RoleMembership> roleMemberships = roleMembershipRepository.findAllByRole(role);
        return roleMemberships;
    }

}
