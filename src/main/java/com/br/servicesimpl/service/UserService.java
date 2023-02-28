package com.br.servicesimpl.service;

import com.br.domain.entity.Role;
import com.br.domain.entity.RoleMembership;
import com.br.domain.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public interface UserService {

    User createUser(User user);

    User getUserById(Long id);

    User updateUser(User user);

    void deleteUser(Long id);

    Role getRoleForMember(Long userId, Long teamId);

    void assignRoleToMember(Long teamId, Long userId, Long roleId);

    Role getRoleByMembership(Long teamId, Long userId);

    List<RoleMembership> getMembershipsByUserId(Long userId);

    List<User> getUsersByTeamId(Long teamId);

}
