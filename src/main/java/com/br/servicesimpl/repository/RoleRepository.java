package com.br.servicesimpl.repository;

import com.br.domain.entity.Role;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

        Optional<Role> findByName(String name);

        List<Role> findAllByMembershipsUserId(Long userId);

        List<Role> findAllByMembershipsTeamId(Long teamId);
}
