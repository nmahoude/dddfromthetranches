package org.nmx.ddd.dddfromthetranches.infrastructure;

import java.time.LocalDateTime;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;

@Entity
public class TeamEntity {

	@Id
	public String id;
	
	public String name;
	public LocalDateTime createdAt;
	public LocalDateTime updatedAt;
	
	@Version
  Integer version;
	
	public TeamEntity() {
	}
	
	public TeamEntity(Team team) {
		this.updateFrom(team);
	}

	@PrePersist
	private void create() {
		this.createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	private void update() {
		this.updatedAt = LocalDateTime.now();
	}

	public Team toTeam() {
		return new Team(id, name);
	}


	public static TeamEntity from(Team team) {
		TeamEntity te = new TeamEntity();
		te.id = team.id();
		te.name = team.name();
		return te;
	}


	public void updateFrom(Team team) {
		id = team.id();
		name = team.name();
	}
	
}
