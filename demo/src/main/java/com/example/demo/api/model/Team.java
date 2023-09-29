package com.example.demo.api.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "teams")
public class Team {

    @Id
    private int id;
    private String teamName;
    //private List<Athlete> roster;

    public Team(int id, String teamName) {
        this.id = id;
        this.teamName = teamName;
        //this.roster = new ArrayList<Athlete>();
    }

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    // public List<Athlete> getRoster() {
    //     return roster;
    // }

    // public void addAthlete(Athlete athlete) {
    //     roster.add(athlete);
    // }

}