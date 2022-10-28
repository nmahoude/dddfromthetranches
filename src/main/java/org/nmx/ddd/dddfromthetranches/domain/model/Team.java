package org.nmx.ddd.dddfromthetranches.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/*
 * DDD Entity
 * 
 * Aggregate root
 * 
 */
public class Team {
	private TeamId id;
	private String name;
	private List<Member> members = new ArrayList<>();
	
	public Team(TeamId id, String name) {
		this(id, name, new ArrayList<>());
	}
	
	public Team(TeamId id, String name, List<Member> members) {
		this.id = id;
		this.name = name;
		this.members = members;
	}

	public TeamId id() {
		return id;
	}

	public String name() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public void addMember(String memberName) {
		this.members.add(new Member(UUID.randomUUID().toString(), memberName));
	}

	public List<Member> members() {
		return Collections.unmodifiableList(members);
	}
}
