package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.*;
import com.example.demo.api.model.Athlete;
import com.example.demo.api.model.Team;
import com.example.demo.repository.TeamRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;


@Service
public class TeamService {
    private final String eaglesEndpoint = "https://sports.core.api.espn.com/v2/sports/football/leagues/nfl/seasons/2023/teams/21";
    private final String PROTOCOL = "https://";
    private final String site = PROTOCOL + "site.api.espn.com/apis/site/v2/sports/football/nfl";
    private final String web = PROTOCOL + "site.web.api.espn.com/apis/common/v3/sports/football/nfl";
    private final String core = PROTOCOL + "sports.core.api.espn.com/v2/sports/football/leagues/nfl";
    private final String rosterEndpoint = "https://site.api.espn.com/apis/site/v2/sports/football/nfl/teams/21/roster";

    private final TeamRepository teamRepo;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    public TeamService(TeamRepository teamRepo) {
        this.teamRepo = teamRepo;
    }

    //Pull specific team data from ESPN API given a teamId
    public Mono<JsonNode> fetchTeamData(String teamId) {
        return webClientBuilder.build()
            .get()
            .uri(site + "/teams/" + teamId)
            .retrieve()
            .bodyToMono(JsonNode.class);
    }

    //Process instance of team data
    public void processTeamData(String teamId) {
        Mono<JsonNode> responseMono = fetchTeamData(teamId);

        responseMono.subscribe(
            body -> {
                try {
                    JsonNode rootNode = body;
                    JsonNode teamNode = rootNode.get("team");
                    String teamName = teamNode.get("displayName").asText();
                    System.out.println(teamName + ", Standing: " + teamNode.get("standingSummary").asText());
                } catch (Exception e) {}
            },
            error -> {
                error.printStackTrace();
            }
        );
    }

    //Get roster from espn api for a given teamId
    public Mono<JsonNode> fetchRosterData(String teamId) {
        return webClientBuilder.build()
            .get()
            .uri(site + "/teams/" + teamId + "/roster")
            .retrieve()
            .bodyToMono(JsonNode.class);
            //.map(jsonNode -> jsonNode.get("athletes").toString());
    }

    //Parese json response representing roster from espn api
    public void processRoster(String teamId) {
        Mono<JsonNode> responseMono = fetchRosterData(teamId);

        responseMono.subscribe(
            body -> {
                try {
                    JsonNode rootNode = body;
                    JsonNode athletesNode = rootNode.get("athletes");
                    for (JsonNode athleteNode : athletesNode) {
                        JsonNode itemsNode = athleteNode.get("items");
                        for (JsonNode itemNode : itemsNode) {
                            String fullName = itemNode.get("fullName").asText();
                            System.out.println("Full Name: " + fullName);
                        }
                    }
                    //athletes = mapper.readValue(body.toString(), new TypeReference<List<Athlete>>(){});
                    //athletes.forEach(athlete -> System.out.println(athlete));
                } catch (Exception e) {}
            },
            error -> {
                error.printStackTrace();
            }
        );
    }

    //Save team to mongodb
    public Team saveTeam(Team team) {
        System.out.println("Saving team: ");
        return teamRepo.save(team);
    }

    //Get all teams from mongodb
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }


}
