package org.nmx.ddd.dddfromthetranches.infrastructure;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;
import org.nmx.ddd.dddfromthetranches.domain.ports.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTeamRepository implements TeamRepository {
	ThreadLocal<Map<Team, TeamEntity>> references = ThreadLocal.withInitial(() -> new HashMap<>());
	
	@Autowired
	SpringJpaRepository springRepo;
	
	
	@Override
	public Team load(String teamId) {
		TeamEntity entity = springRepo.findById(teamId).orElseThrow();
		Team team = entity.toTeam();
		
		references.get().put(team, entity);
		
		return team;
	}
	
	@Override
	public void save(Team team) {
		TeamEntity teamE = references.get().computeIfAbsent(team, t -> new TeamEntity(team));
		
		teamE.updateFrom(team);
		
		// technical considerations , not domain ....
		if (! springRepo.existsById(team.getId())) {
			teamE.createdAt = LocalDateTime.now(); 
		} else {
			teamE.updatedAt = LocalDateTime.now();
		}
		
		TeamEntity e = springRepo.save(teamE); // really save the entity and get back the managed one
		references.get().put(team, e); // put the correct entity (managed) in the references
		// our domain object has not changed !
	}
}
