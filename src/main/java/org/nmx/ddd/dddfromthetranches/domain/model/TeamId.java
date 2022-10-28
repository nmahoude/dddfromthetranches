package org.nmx.ddd.dddfromthetranches.domain.model;

public record TeamId(String id) {
	
	public static TeamId of(String id) {
		// invariants
		if (id == null) {
			throw new IllegalArgumentException("team id can't be null");
		}
		
		return new TeamId(id);
	}
	
}
