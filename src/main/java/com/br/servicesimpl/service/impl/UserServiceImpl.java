package com.br.servicesimpl.service.impl;

import com.br.domain.entity.Role;
import com.br.domain.entity.RoleMembership;
import com.br.domain.entity.User;
import com.br.servicesimpl.repository.RoleRepository;
import com.br.servicesimpl.repository.RoleMembershipRepository;
import com.br.servicesimpl.repository.UserRepository;
import com.br.servicesimpl.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

        private final UserRepository userRepository;

        private final RoleRepository roleRepository;
        private final RoleMembershipRepository roleMembershipRepository;

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    static {
        LOGGER.setLevel(Level.INFO);
    }
        public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, RoleMembershipRepository roleMembershipRepository) {
            this.userRepository = userRepository;
            this.roleRepository = roleRepository;
            this.roleMembershipRepository = roleMembershipRepository;
        }

        @Override
        public User createUser(User user) {

            return userRepository.save(user);
        }

        @Override
        public User getUserById(Long id) {
            return userRepository.findById(id).orElse(null);
        }

        @Override
        public User updateUser(User user) {
            return userRepository.save(user);
        }

        @Override
        public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }

        @Override
        public void assignRoleToMember(Long teamId, Long userId, Long roleId) {
            Optional<RoleMembership> optionalRoleMembership = roleMembershipRepository.findByTeamIdAndUserId(teamId, userId);
            if (optionalRoleMembership.isPresent()) {
                RoleMembership roleMembership = optionalRoleMembership.get();
                roleMembership.setRoleId(roleId);
                roleMembershipRepository.save(roleMembership);
            }
        }

        @Override
        public Role getRoleByMembership(Long teamId, Long userId) {
            Optional<RoleMembership> optionalRoleMembership = roleMembershipRepository.findByTeamIdAndUserId(teamId, userId);
            if (optionalRoleMembership.isPresent()) {
                return optionalRoleMembership.get().getRole();
            }
            return null;
        }

        @Override
        public List<RoleMembership> getMembershipsByUserId(Long userId) {
            return roleMembershipRepository.findByUserId(userId);
        }

        @Override
        public List<User> getUsersByTeamId(Long teamId) {
            List<RoleMembership> roleMemberships = roleMembershipRepository.findByTeamId(teamId);
            List<User> users = new ArrayList<>();
            for (RoleMembership roleMembership : roleMemberships) {
                users.add(roleMembership.getUser());
            }
            return users;
        }


    @Override
    public Role getRoleForMember(Long userId, Long teamId) {
        Optional<RoleMembership> optionalRoleMembership = roleMembershipRepository.findByTeamIdAndUserId(teamId, userId);
        if (optionalRoleMembership.isPresent()) {
            RoleMembership roleMembership = optionalRoleMembership.get();
            return roleMembership.getRole();
        }
        return null;
    }


}