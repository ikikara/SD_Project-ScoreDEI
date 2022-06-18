package com.example.data;

import java.util.List;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String type;
    @ManyToOne
    private Game game;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    @ManyToOne
    private Team team;
    @ManyToOne
    private Player player;
    private int valid;

    public Event() {
        this.valid = 0;
    }

    public Event(String type, Game game, LocalDateTime date, Team team, Player player){
        this.type = type;
        this.game = game;
        this.date = date;
        this.team = team;
        this.player = player;
    }
 
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public Game getGame(){
        return this.game;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public void setDate(LocalDateTime date){
        this.date = date;
    }

    public Player getPlayer(){
        return this.player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Team getTeam(){
        return this.team;
    }

    public void setTeam(Team team){
        this.team = team;
    }

    public int getValid(){
        return this.valid;
    }

    public void setValid(int valid){
        this.valid = valid;
    }
}