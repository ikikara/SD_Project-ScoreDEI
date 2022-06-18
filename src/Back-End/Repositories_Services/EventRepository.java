package com.example.demo;

import java.util.*;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.data.Event;
import com.example.data.Game;
import com.example.data.Player;

public interface EventRepository extends CrudRepository<Event, Integer>   { 
       @Query("select g from Game g where g.id = ?1")
       public Game findGameById(int id);

       @Query("select e from Event e where e.game = ?1 and e.valid = 1 order by e.date asc")
       public List<Event> findValidEventsById(Game game);

       @Query("select e from Event e where e.valid = 0 ")
       public List<Event> findToValidEventsById();

       @Transactional
       @Modifying
       @Query("update Game g set g.status = ?1 where g.id = ?2")
       public void setGameStatus(String status, int id);

       @Transactional
       @Modifying
       @Query("update Event e set valid = ?1 where e.id = ?2")
       public void setEventValidation(int valid, int id);

       @Transactional
       @Modifying
       @Query("delete from Event e where e.id = ?1 ")
       public void deleteEventById(int id);

       @Query("select count(e)" +
              " from Event e" +
              " where e.type = 'Yellow Card'" +
              " and e.player = ?1" +
              " and e.game = ?2" +
              " and e.valid = 1")
       public int nrYellowCardsPlayerGame(Player player, Game game);

       @Query("select count(e)" +
              " from Event e" +
              " where e.type = 'Red Card'" +
              " and e.player = ?1" +
              " and e.game = ?2" +
              " and e.valid = 1")
       public int nrRedCardsPlayerGame(Player player, Game game);

       @Query("select count(e) from Event e where e.type = 'Goal' and e.valid = 1")
       public int numberGoals();
           
} 