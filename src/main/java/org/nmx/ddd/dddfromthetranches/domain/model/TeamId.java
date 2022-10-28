package org.nmx.ddd.dddfromthetranches.domain.model;

public record TeamId(String id) {
	
	public static TeamId of(String id) {
		return new TeamId(id);
	}
	
}
