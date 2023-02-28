package com.br.servicesimpl.repository;

import com.br.domain.entity.Team;
import com.br.domain.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByMembersContaining(User user);

}
