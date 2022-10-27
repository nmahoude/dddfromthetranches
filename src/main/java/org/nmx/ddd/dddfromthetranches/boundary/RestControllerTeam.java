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
	public Team get(@PathVariable("id")String id) {
		
		return teams.load(id);
	}
	
	@PostMapping("/teams")
	public Team save(@RequestBody Team team) {
		// have to put the technical id from the code
		team.setId("0");
		
		teams.save(team);
		return team;
	}
	
	@PutMapping("/teams/{id}")
	public Team update(@PathVariable("id")String id, @RequestBody Team updatedTeam) {
		Team t = teams.load(id);
		
		t.setName(updatedTeam.getName());
		
		teams.save(t);
		return t;
	}
	
}
