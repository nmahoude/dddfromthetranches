package org.nmx.ddd.dddfromthetranches.domain.ports;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;

public interface TeamRepository {

	void put(Team team);
	Team get(String teamId);

}
