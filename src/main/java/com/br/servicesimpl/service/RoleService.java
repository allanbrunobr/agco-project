package com.br.servicesimpl.service;

import com.br.domain.entity.Role;
import com.br.domain.entity.RoleMembership;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    Role createRole(Role role);
    Role getRoleById(Long id);
    Role updateRole(Role role);
    void deleteRole(Long id);
    void assignRoleToMember(Long memberId, Long teamId, Long roleId) throws ClassNotFoundException;
    Role getRoleByMembership(Long userId, Long teamId);

    List<Role> getAllRoles();
    List<Role> findAllByMembershipsUserId(Long userId);

    List<Role> findAllByMembershipsTeamId(Long teamId);

    List<RoleMembership> getMembershipsByRoleId(Long roleId);

}
