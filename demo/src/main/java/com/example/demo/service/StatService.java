package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.*;
import com.example.demo.api.model.Athlete;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;


@Service
public class StatService {
    private final String eaglesEndpoint = "https://sports.core.api.espn.com/v2/sports/football/leagues/nfl/seasons/2023/teams/21";
    private final String PROTOCOL = "https://";
    private final String site = PROTOCOL + "site.api.espn.com/apis/site/v2/sports/football/nfl";
    private final String web = PROTOCOL + "site.web.api.espn.com/apis/common/v3/sports/football/nfl";
    private final String core = PROTOCOL + "sports.core.api.espn.com/v2/sports/football/leagues/nfl";
    private final String rosterEndpoint = "https://site.api.espn.com/apis/site/v2/sports/football/nfl/teams/1/roster";

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<JsonNode> fetchTeamData(String teamId) {
        return webClientBuilder.build()
            .get()
            .uri(site + "/teams/" + teamId + "/roster")
            .retrieve()
            .bodyToMono(JsonNode.class);
            //.map(jsonNode -> jsonNode.get("athletes").toString());
    }

    public void extractAndProcessData(String teamId) {
        Mono<JsonNode> responseMono = fetchTeamData(teamId);
        ObjectMapper mapper = new ObjectMapper();

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
}
