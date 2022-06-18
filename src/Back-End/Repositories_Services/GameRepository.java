package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import com.example.data.Game;
import com.example.data.Team;

public interface GameRepository extends CrudRepository<Game, Integer>{
    @Query("select count(e) from Event e where e.game = ?1 and e.team = ?2 and e.type = 'Yellow Card' and e.valid = 1")
    public int yellowCardByTeamInAGame(Game game, Team team);

    @Query("select count(e) from Event e where e.game = ?1 and e.team = ?2 and e.type = 'Red Card' and e.valid = 1")
    public int redCardByTeamInAGame(Game game, Team team);

    @Query("select count(e) from Event e where e.game = ?1 and e.team = ?2 and e.type = 'Goal' and e.valid = 1")
    public int goalsByTeamInAGame(Game game, Team team);

    @Query("select count(g) from Game g where g.status = 'Finished'")
    public int numberGames();

    @Query("select g from Game g where (g.teamA = ?1 and g.teamB = ?2 or g.teamA = ?2 and g.teamB = ?1) and g.status = 'Finished'")
    public List<Game> gamesBetweenTeams(Team tA, Team tb);
    
} 
