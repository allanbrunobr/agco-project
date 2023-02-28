package com.br.servicesimpl.service.impl;

import com.br.domain.entity.Role;
import com.br.domain.entity.RoleMembership;
import com.br.servicesimpl.repository.RoleRepository;
import com.br.servicesimpl.repository.RoleMembershipRepository;
import com.br.servicesimpl.service.RoleMembershipService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleMembershipServiceImpl implements RoleMembershipService {

    private final RoleMembershipRepository roleMembershipRepository;

    private final RoleRepository roleRepository;


    @Override
    public List<RoleMembership> getRoleMembershipsByRoleId(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("Role not found"));
        return roleMembershipRepository.findAllByRole(role);
    }
    @Override
    public List<RoleMembership> getAllRoleMemberships() {
        return roleMembershipRepository.findAll();
    }



    @Override
    public List<RoleMembership> getMembershipsByUserId(Long userId) {
        return roleMembershipRepository.findByUserId(userId);
    }

    @Override
    public List<RoleMembership> getMembershipsByTeamId(Long teamId) {
        return roleMembershipRepository.findByTeamId(teamId);
    }

    @Override
    public List<RoleMembership> getMembershipsByRoleId(Long roleId) {
        return roleMembershipRepository.findByRoleId(roleId);
    }

    @Override
    public RoleMembership createRoleMembership(RoleMembership roleMembership) {
        return roleMembershipRepository.save(roleMembership);
    }

    @Override
    public Optional<RoleMembership> findByUserIdAndTeamIdAndRoleId(Long userId, Long teamId, Long roleId) {
        return roleMembershipRepository.findByUserIdAndTeamIdAndRoleId(userId, teamId, roleId);
    }
}