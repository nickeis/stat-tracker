package com.example.demo.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//import javax.persistence.Embedded;
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "athletes")
public class Athlete {
    
    @JsonProperty("id")
    @Id
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
