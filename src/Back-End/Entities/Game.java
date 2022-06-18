package com.example.data;

import java.util.List;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import net.bytebuddy.asm.Advice.Local;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Game {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private Team teamA;
    @ManyToOne
    private Team teamB;
    private String location;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    @OneToMany(mappedBy="game")
    private List<Event> events;
    String status;
    //private Team team;

    public Game() {}

    public Game(Team teamA, int goalsA, Team teamB, int goalsB, String location, LocalDateTime date){
        this.teamA = teamA;
        this.teamB = teamB;
        this.location = location;
        this.date = date;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Team getTeamA(){
        return this.teamA;
    }

    public void setTeamA(Team teamA){
        this.teamA = teamA;
    }
    
    public Team getTeamB(){
        return this.teamB;
    }

    public void setTeamB(Team teamB){
        this.teamB = teamB;
    }

    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location){
        this.location = location;
    }
 
    public LocalDateTime getDate(){
        return this.date;
    }

    public void setDate(LocalDateTime date){
        this.date = date;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvent(List<Event> events) {
        this.events = events;
    }
    
    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
