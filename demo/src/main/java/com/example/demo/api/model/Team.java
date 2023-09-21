package com.example.demo.api.model;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private String id;
    private String teamName;
    private List<Athlete> roster;

    public Team(String id, String teamName) {
        this.id = id;
        this.teamName = teamName;
        this.roster = new ArrayList<Athlete>();
    }

    public String getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Athlete> getRoster() {
        return roster;
    }

    public void addAthlete(Athlete athlete) {
        roster.add(athlete);
    }

}