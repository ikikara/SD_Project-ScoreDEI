package com.example.data;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;



@Entity
public class Team{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String logo;
    @OneToMany(mappedBy = "team")
    private List<Player> players;
    private int numberGames;
    private int numberVic;
    private int numberdef;
    private int numberImp;

    public Team() {}

    public Team(String name){
        this.name = name;
        this.numberGames = 0;
        this.numberVic = 0;
        this.numberdef = 0;
        this.numberImp = 0;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getNumberGames() {
        return numberGames;
    }

    public void setNumberGames(int numberGames) {
        this.numberGames = numberGames;
    }

    public int getNumberVic() {
        return numberVic;
    }

    public void setNumberVic(int numberVic) {
        this.numberVic = numberVic;
    }

    public int getNumberdef() {
        return numberdef;
    }

    public void setNumberdef(int numberdef) {
        this.numberdef = numberdef;
    }

    public int getNumberImp() {
        return numberImp;
    }

    public void setNumberImp(int numberImp) {
        this.numberImp = numberImp;
    }

    public List<Player> getPlayers(){
        return this.players;
    }

}