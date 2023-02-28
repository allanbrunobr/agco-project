package com.br.servicesimpl.service;

import com.br.domain.entity.Team;
import com.br.domain.entity.User;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public interface TeamService {

    Team createTeam(Team team);

    Team getTeamById(Long id);

    Team updateTeam(Team team);

    void deleteTeam(Long id);

    void addMemberToTeam(Long teamId, Long memberId);

    void removeMemberFromTeam(Long teamId, Long memberId);

    List<User> getMembersByTeamId(Long teamId);

    List<Team> getTeamsByUserId(Long userId);

}
