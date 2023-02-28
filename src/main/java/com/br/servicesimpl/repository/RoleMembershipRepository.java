package com.br.servicesimpl.repository;

import com.br.domain.entity.Role;
import com.br.domain.entity.RoleMembership;
import com.br.domain.entity.Team;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleMembershipRepository extends JpaRepository<RoleMembership, Long> {

    Optional<RoleMembership> findByUserIdAndTeamId(Long userId, Long teamId);

    List<RoleMembership> findByUserId(Long userId);

    List<RoleMembership> findByTeamId(Long teamId);

    @Query("SELECT tm FROM RoleMembership tm WHERE tm.team.id = :teamId AND tm.role.id = :roleId")
    List<RoleMembership> findByTeamIdAndRoleId(Long teamId, Long roleId);

    @Query("SELECT tm FROM RoleMembership tm WHERE tm.role.id = :roleId")
    List<RoleMembership> findByRoleId(Long roleId);

    @Query("SELECT tm FROM RoleMembership tm WHERE tm.user.id = :userId AND tm.team.id = :teamId AND tm.role.id = :roleId")
    Optional<RoleMembership> findByUserIdAndTeamIdAndRoleId(@Param("userId") Long userId, @Param("teamId") Long teamId, @Param("roleId") Long roleId);

    List<RoleMembership> findAll();

    default boolean existsByTeamIdAndUserId(Long teamId, Long userId) {
        return findByUserIdAndTeamId(userId, teamId).isPresent();
    }

    List<Team> findAllTeamsByUserId(Long userId);

    List<RoleMembership> findAllByRole(Role role);

    Optional<RoleMembership> findByTeamIdAndUserId(Long teamId, Long userId);

    List<RoleMembership> findAllByUserId(Long userId);

}
