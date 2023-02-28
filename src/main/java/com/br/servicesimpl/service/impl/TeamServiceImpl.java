package com.br.servicesimpl.service.impl;

import com.br.domain.entity.Team;
import com.br.domain.entity.Membership;
import com.br.domain.entity.User;
import com.br.servicesimpl.repository.MembershipRepository;
import com.br.servicesimpl.repository.TeamRepository;
import com.br.servicesimpl.repository.UserRepository;
import com.br.servicesimpl.service.TeamService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    private final MembershipRepository membershipRepository;

    private static final Logger LOGGER = Logger.getLogger(TeamServiceImpl.class.getName());

    static {
        LOGGER.setLevel(Level.INFO);
    }

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository,
                           UserRepository userRepository, MembershipRepository membershipRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
    }

    public Team createTeam(Team team) {

        return teamRepository.save(team);
    }

    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Team not found"));
    }

    public Team updateTeam(Team team) {
        Team existingTeam = getTeamById(team.getId());
        existingTeam.setName(team.getName());
        existingTeam.setLeader(team.getLeader());
        existingTeam.setMembers(team.getMembers());
        return teamRepository.save(existingTeam);
    }

    public void deleteTeam(Long id) {
        Team existingTeam = getTeamById(id);
        teamRepository.delete(existingTeam);
    }

    public void addMemberToTeam(Long teamId, Long memberId) {
        Team existingTeam = getTeamById(teamId);
        User existingUser = userRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        existingTeam.getMembers().add(existingUser);
        teamRepository.save(existingTeam);
    }

    public Optional<Membership> findTeamMembersByTeamAndUser(Long teamId, Long userId) {
        return membershipRepository.findByTeamIdAndUserId(teamId, userId);
    }

    public void removeMemberFromTeam(Long teamId, Long memberId) {
        Team existingTeam = getTeamById(teamId);
        User existingUser = userRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        existingTeam.getMembers().remove(existingUser);
        teamRepository.save(existingTeam);
    }

    @Override
    public List<User> getMembersByTeamId(Long teamId) {
        // Implementação do método para listar todos os membros de uma equipe
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isPresent()) {
            return team.get().getMembers();
        } else {
            throw new EntityNotFoundException("Team not found");
        }
    }

    public List<Team> getTeamsByUserId(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return teamRepository.findByMembersContaining(existingUser);
    }
}