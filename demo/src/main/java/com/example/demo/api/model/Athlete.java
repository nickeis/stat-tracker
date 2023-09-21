package com.example.demo.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//import javax.persistence.Embedded;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Athlete {
    
    @JsonProperty("id")
    private String id;
    @JsonProperty("displayName")
    private String displayName;

    //TODO: Embedded fields for position, team, etc.
    //@Embedded
    //private Position position;

    public Athlete() {
    }

    public Athlete(String id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return displayName;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "id='" + id + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
