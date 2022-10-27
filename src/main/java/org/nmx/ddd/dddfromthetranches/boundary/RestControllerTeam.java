package org.nmx.ddd.dddfromthetranches.boundary;

import org.nmx.ddd.dddfromthetranches.domain.model.Team;
import org.nmx.ddd.dddfromthetranches.domain.ports.TeamRepository;
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
	TeamRepository teams;
	
	@GetMapping("/teams/{id}")
	public TeamDto get(@PathVariable("id")String id) {
		
		return TeamDto.from(teams.get(id));
	}
	
	@PostMapping("/teams")
	public TeamDto save(@RequestBody TeamDto dto) {
		// have to put the technical id from the code
		
		Team team = new Team("0", dto.name);
		
		teams.put(team);
		return TeamDto.from(team);
	}
	
	@PutMapping("/teams/{id}")
	public TeamDto update(@PathVariable("id")String id, @RequestBody TeamDto updatedTeam) {
		Team t = teams.get(id);
		
		t.setName(updatedTeam.name);
		
		teams.put(t);
		return TeamDto.from(t);
	}
	
}
