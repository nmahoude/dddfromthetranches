package org.nmx.ddd.dddfromthetranches.boundary;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;

public class TeamDto {

	public String id;
	public String name;

	public static TeamDto from(Team team) {
		TeamDto dto = new TeamDto();
		dto.id = team.id();
		dto.name = team.name();
		
		return dto;
	}

}
