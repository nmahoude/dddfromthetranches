package org.nmx.ddd.dddfromthetranches.infrastructure;

import java.time.LocalDateTime;
import java.util.UUID;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;
import org.nmx.ddd.dddfromthetranches.domain.ports.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTeamRepository implements TeamRepository {

	@Autowired
	SpringJpaRepository springRepo;
	
	
	@Override
	public Team load(String teamId) {
		return springRepo.getReferenceById(teamId).toTeam();
	}
	
	@Override
	public void save(Team team) {
		TeamEntity teamE = TeamEntity.from(team);
		
		if (teamE.id == null) {
			teamE.id = UUID.randomUUID().toString();
			teamE.createdAt = LocalDateTime.now();
		} else {
			teamE.updatedAt = LocalDateTime.now();
		}
		
		TeamEntity e = springRepo.save(teamE);
		team.setId(e.id);
	}
}
