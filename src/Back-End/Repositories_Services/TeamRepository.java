package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import com.example.data.Team;

public interface TeamRepository extends CrudRepository<Team, Integer>{
    @Query("select t from Team t where t.name like %?1")
    public Team findByName(String chars);

    @Query("select count(t)" +
           " from Team t" +
           " where t.name = ?1" )
    public int teamExistsByName(String name);

    @Transactional
    @Modifying
    @Query("update Team t set numberVic = numberVic + ?1 where t.id = ?2")
    public void setTeamVictories(int numV, int id);

    @Transactional
    @Modifying
    @Query("update Team t set numberdef = numberdef + ?1 where t.id = ?2")
    public void setTeamDefeats(int numD, int id);

    @Transactional
    @Modifying
    @Query("update Team t set numberImp = numberImp + ?1 where t.id = ?2")
    public void setTeamTies(int numT, int id);

    @Transactional
    @Modifying
    @Query("update Team t set numberGames = numberGames + ?1 where t.id = ?2")
    public void setTeamGames(int numG, int id);

    @Transactional
    @Modifying
    @Query("update Team t set name = ?1, logo = ?2 where t.id = ?3")
    public void updateTeam(String name, String logo, int id);
} 