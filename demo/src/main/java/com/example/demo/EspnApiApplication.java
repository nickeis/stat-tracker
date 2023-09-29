package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.demo.repository.TeamRepository;

@SpringBootApplication
@EnableMongoRepositories
public class EspnApiApplication {

	@Autowired
    TeamRepository athleteRepo;

	public static void main(String[] args) {
		SpringApplication.run(EspnApiApplication.class, args);
	}

}
