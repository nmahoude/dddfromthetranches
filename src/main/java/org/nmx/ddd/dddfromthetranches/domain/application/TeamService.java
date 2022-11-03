package org.nmx.ddd.dddfromthetranches.domain.application;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;
import org.nmx.ddd.dddfromthetranches.domain.model.TeamId;
import org.nmx.ddd.dddfromthetranches.domain.ports.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

	private final TeamRepository teamsRepository;
	
	@Autowired
	public TeamService(TeamRepository teamRepository) {
		teamsRepository = teamRepository;
	}

	public Team get(TeamId id) {
		return teamsRepository.get(id);
	}

	public void put(Team team) {
		teamsRepository.put(team);
	}

	public Team updateName(TeamId teamId, String name) {
		Team t = teamsRepository.get(teamId);
		t.updateName(name);
		
		teamsRepository.put(t);
		return t;
	}

	public Team addMember(TeamId id, String name) {
		Team t = teamsRepository.get(id);

		t.addMember(name);
		
		teamsRepository.put(t);
		return t;
	}

	public Team create(String name) {
		String nextId = "0"; // CAN DO : inject an id generator or asks the TeamsRepository 
		
		Team team = new Team(TeamId.of(nextId), name);
		teamsRepository.put(team);
		return team;
	}

}
