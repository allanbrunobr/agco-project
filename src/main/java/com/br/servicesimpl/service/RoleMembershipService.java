package com.br.servicesimpl.service;

import com.br.domain.entity.RoleMembership;
import com.br.servicesimpl.exceptions.RoleMembershipException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface RoleMembershipService {
    List<RoleMembership> getRoleMembershipsByRoleId(Long roleId);

    List<RoleMembership> getAllRoleMemberships();
    List<RoleMembership> getMembershipsByUserId(Long userId);
    List<RoleMembership> getMembershipsByTeamId(Long teamId);
    List<RoleMembership> getMembershipsByRoleId(Long roleId);
    RoleMembership createRoleMembership(RoleMembership roleMembership) throws RoleMembershipException;

    Optional<RoleMembership> findByUserIdAndTeamIdAndRoleId(Long userId, Long teamId, Long roleId);



}

