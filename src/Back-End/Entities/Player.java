package com.example.data;

import java.util.List;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class Player{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name, position;
    private Date birthdate;
    @ManyToOne
    private Team team;

    public Player() {}

    public Player(String name, String position, Date birthdate, Team team){
        this.name = name;
        this.position = position;
        this.birthdate = birthdate;
        this.team = team;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition(){
        return position;
    }

    public void setPosition(String position){
        this.position = position;
    }

    public Date getBirthdate(){
        return birthdate;
    }

    public void setBirthdate(Date birthdate){
        this.birthdate = birthdate;
    }
    
    public Team getTeam(){
        return team;
    }

    public void setTeam(Team team){
        this.team = team;
    }
}