package com.example.demo;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.data.Team;
import com.example.data.Player;

public interface PlayerRepository extends CrudRepository<Player, Integer>{
    @Query("select p" +
           " from Player p" +
           " where p.name like %?1")
    public Player findByName(String chars);

    @Query(value = "select p.name, count(e)" +
           " from Event e, Player p" +
           " where e.type = 'Goal' and p.id = e.player_id" +
           " group by p.name" +
           " order by count(e) desc" +
           " limit 1", nativeQuery =  true )
    public String bestScorer();

    @Query("select count(p)" +
           " from Player p" +
           " where p.name = ?1" )
    public int playerExistsByName(String name);

    @Transactional
    @Modifying
    @Query("update Player p set name = ?1, position = ?2, birthdate = ?3, team = ?4 where p.id = ?5")
    public void updatePlayer(String name, String position, Date birthdate, Team team, int id);
} 