package org.nmx.ddd.dddfromthetranches.domain.model;

/*
 * DDD entity (not root of the aggregate)
 */
public class Member {
	private String id;
	private String name;

	public Member(String id, String memberName) {
		this.id = id;
		this.name = memberName;
	}

	public String id() {
		return id;
	}
	
	public String name() {
		return name;
	}
}
