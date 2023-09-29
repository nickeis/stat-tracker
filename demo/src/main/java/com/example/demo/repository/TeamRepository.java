package com.example.demo.repository;

import com.example.demo.api.model.Team;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface TeamRepository extends MongoRepository<Team, Integer> {
    
    @Query("{name:'?0'}")
    Team findItemByName(String name);
    
    @Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
    List<Team> findAll(String category);
    
    public long count();

}
