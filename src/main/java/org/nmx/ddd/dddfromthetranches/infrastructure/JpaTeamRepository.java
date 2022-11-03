package org.nmx.ddd.dddfromthetranches.infrastructure;

import java.time.LocalDateTime;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;
import org.nmx.ddd.dddfromthetranches.domain.model.TeamId;
import org.nmx.ddd.dddfromthetranches.domain.ports.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTeamRepository implements TeamRepository {
	
	@Autowired
	SpringJpaRepository springRepo;
	
	
	@Override
	public Team get(TeamId teamId) {
		TeamEntity entity = springRepo.findById(teamId.id()).orElseThrow();
		return entity.toTeam();
	}
	
	@Override
	public void put(Team team) {
		TeamEntity teamE = springRepo.getReferenceById(team.id().id());  // check if it is loaded in spring jpa context
		if (teamE.id == null) { // if not, create a new one
			teamE = new TeamEntity();
			teamE.id = team.id().id();
		}
		
		teamE.updateFrom(team); // update from the domain model
		
		// technical considerations , not domain ....
		// can use prepersist & preupdate for this ones but, for the demo, I let them here
		if (! springRepo.existsById(team.id().id())) {
			teamE.createdAt = LocalDateTime.now(); 
		} else {
			teamE.updatedAt = LocalDateTime.now();
		}
		
		TeamEntity e = springRepo.save(teamE); // really save the entity and get back the managed one but our domain object has not changed !
	}
}

interface SpringJpaRepository extends JpaRepository<TeamEntity, String>{

}

