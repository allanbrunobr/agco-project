package com.br.servicesimpl.repository;

import com.br.domain.entity.Membership;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Optional<Membership> findByTeamIdAndUserId(Long teamId, Long userId);
}
