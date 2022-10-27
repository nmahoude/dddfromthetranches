package org.nmx.ddd.dddfromthetranches.domain.model;

public class Team {
	private String id;
	private String name;
	
	public Team(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String id() {
		return id;
	}

	public String name() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
