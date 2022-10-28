package org.nmx.ddd.dddfromthetranches.boundary;

import org.nmx.ddd.dddfromthetranches.domain.application.TeamService;
import org.nmx.ddd.dddfromthetranches.domain.model.Team;
import org.nmx.ddd.dddfromthetranches.domain.model.TeamId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerTeam {

	@Autowired
	TeamService teams;
	
	
	@GetMapping("/teams/{id}")
	public TeamDto get(@PathVariable("id")String id) {
		
		Team team = teams.get(TeamId.of(id));
		return TeamDto.from(team);
	}
	
	@PostMapping("/teams")
	public TeamDto save(@RequestBody TeamDto dto) {
		// have to put the technical id from the code
		
		Team team = teams.create(dto.name);
		return TeamDto.from(team);
	}
	
	@PutMapping("/teams/{id}")
	public TeamDto update(@PathVariable("id")String id, @RequestBody TeamDto updatedTeam) {

		Team t = teams.updateName(TeamId.of(id), updatedTeam.name);

		return TeamDto.from(t);
	}

	@PostMapping("/teams/{id}/members")
	public TeamDto addMember(@PathVariable("id")String id, @RequestBody String name) {
		Team t = teams.addMember(TeamId.of(id), name);
		
		return TeamDto.from(t);
	}

}
