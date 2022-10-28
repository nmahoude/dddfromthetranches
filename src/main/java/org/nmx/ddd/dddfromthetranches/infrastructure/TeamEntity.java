package org.nmx.ddd.dddfromthetranches.infrastructure;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;
import org.nmx.ddd.dddfromthetranches.domain.model.TeamId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name="TEAM")
public class TeamEntity {

	// domain state
	@Id
	public String id;
	public String name;
	@OneToMany(cascade = CascadeType.ALL)
	public List<MemberEntity> members = new ArrayList<>();
	

	// technical data
	public LocalDateTime createdAt;
	public LocalDateTime updatedAt;
	@Version
  Integer version;
	
	public TeamEntity() {
	}
	
	public TeamEntity(Team team) {
		this.updateFrom(team);
	}

	/*
	 Done in the repository for demo purpose, could be here  
	@PrePersist
	private void create() {
		this.createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	private void update() {
		this.updatedAt = LocalDateTime.now();
	}
	*/
	
	public Team toTeam() {
		return new Team(TeamId.of(id), name, new ArrayList<>(members.stream().map(MemberEntity::toMember).toList()));
	}

	public void updateFrom(Team team) {
		id = team.id().id();
		name = team.name();
		
		this.members.clear();
		this.members.addAll(team.members().stream().map(MemberEntity::from).toList());
	}
	
}
