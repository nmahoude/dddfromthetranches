package org.nmx.ddd.dddfromthetranches.infrastructure;

import java.time.LocalDateTime;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TeamEntity {

	@Id
	public String id;
	
	public String name;
	public LocalDateTime createdAt;
	public LocalDateTime updatedAt;
	
	
	public Team toTeam() {
		return new Team(id, name);
	}


	public static TeamEntity from(Team team) {
		TeamEntity te = new TeamEntity();
		te.id = team.getId();
		te.name = team.getName();
		return te;
	}
	
}
