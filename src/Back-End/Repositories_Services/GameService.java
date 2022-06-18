package com.example.demo;

import java.util.*;

import javax.transaction.Transactional;
   
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;


import com.example.data.Game;
import com.example.data.Team;

@Service    
public class GameService   
{    
    @Autowired    
    private GameRepository gameRepository;

    public void addGame(Game game)  {    
        gameRepository.save(game);    
    }

    public List<Game> getAllGames()  {    
        List<Game> allgames = new ArrayList<>();    
        gameRepository.findAll().forEach(allgames::add);    
        return allgames;    
    }

    public Optional<Game> getGame(int id) {
        return gameRepository.findById(id);
    }
    
    public int yellowCardByTeamInAGame(Game game, Team team){
        return this.gameRepository.yellowCardByTeamInAGame(game, team);
    }

    public int redCardByTeamInAGame(Game game, Team team){
        return this.gameRepository.redCardByTeamInAGame(game, team);
    }

    public int goalsByTeamInAGame(Game game, Team team){
        return this.gameRepository.goalsByTeamInAGame(game, team);
    }

    public int numberGames(){
        return this.gameRepository.numberGames();
    }

    public List<Game> gamesBetweenTeams(Team tA, Team tb){
        return this.gameRepository.gamesBetweenTeams(tA, tb);
    }
}