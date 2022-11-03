package org.nmx.ddd.dddfromthetranches.domain.ports;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;
import org.nmx.ddd.dddfromthetranches.domain.model.TeamId;

public interface TeamRepository {

	void put(Team team);
	Team get(TeamId teamId);

	
}
