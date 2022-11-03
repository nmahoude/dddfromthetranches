package org.nmx.ddd.dddfromthetranches.domain.ports;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.nmx.ddd.dddfromthetranches.domain.application.TeamService;
import org.nmx.ddd.dddfromthetranches.domain.model.Team;
import org.nmx.ddd.dddfromthetranches.domain.model.TeamId;

public class TeamServiceTest {

	@Test
	void ICanSaveATeam() throws Exception {

		TeamRepositoryTestDouble teamRepository = new TeamRepositoryTestDouble();
		//TeamRepository teamRepository = Mockito.mock(TeamRepository.class);
		TeamService ts = new TeamService(teamRepository);
		
		Team team = ts.create("test equipe");
		
		//Assertions.assertThat(teamRepository.entities.values()).contains(team);
	}
	
	static class TeamRepositoryTestDouble implements TeamRepository {
		Map<TeamId, Team> entities = new HashMap<>();
		
		@Override
		public void put(Team team) {
			entities.put(team.id(), team);
		}

		@Override
		public Team get(TeamId teamId) {
			return entities.get(teamId);
		}
	}
}
