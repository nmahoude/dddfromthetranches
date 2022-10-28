package org.nmx.ddd.dddfromthetranches.boundary;

import java.util.ArrayList;
import java.util.List;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;

public class TeamDto {

	public String id;
	public String name;
	public List<String> members = new ArrayList<>();
	
	public static TeamDto from(Team team) {
		TeamDto dto = new TeamDto();
		dto.id = team.id().id();
		dto.name = team.name();
	
		team.members().forEach(m -> dto.members.add(m.name()));
		
		return dto;
	}

}
