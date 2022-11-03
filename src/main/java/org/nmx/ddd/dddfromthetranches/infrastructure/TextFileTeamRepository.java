package org.nmx.ddd.dddfromthetranches.infrastructure;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;
import org.nmx.ddd.dddfromthetranches.domain.model.TeamId;
import org.nmx.ddd.dddfromthetranches.domain.ports.TeamRepository;

public class TextFileTeamRepository implements TeamRepository {

	@Override
	public void put(Team team) {
		
	}

	@Override
	public Team get(TeamId teamId) {
		// TODO Auto-generated method stub
		return null;
	}

}
