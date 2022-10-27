package org.nmx.ddd.dddfromthetranches.domain.ports;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;

public interface TeamRepository {

	void save(Team team);
	Team load(String teamId);

}
