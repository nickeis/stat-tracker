package com.example.demo.api.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.model.Team;
import com.example.demo.service.TeamService;

@RestController
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams/{teamId}")
    public void getTeams(@PathVariable("teamId") String teamId) {
        teamService.processTeamData(teamId);
    }

    @GetMapping("teams/{teamId}/roster")
    public void getRoster(@PathVariable("teamId") String teamId) {
        teamService.processRoster(teamId);
    }

    @PostMapping("/")
    public Team saveTeam(Team team) {
        return teamService.saveTeam(team);
    }

    @GetMapping
    public List<Team> getSavedTeams() {
        return teamService.getAllTeams();
    }
}