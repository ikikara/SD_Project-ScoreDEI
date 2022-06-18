package com.example.demo;

import java.util.*;

import javax.transaction.Transactional;
   
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.data.Player;
import com.example.data.Team;

@Service    
public class PlayerService   
{    
    @Autowired    
    private PlayerRepository playerRepository;

    public void addPlayer(Player player)  
    {    
        playerRepository.save(player);    
    }

    public List<Player> getAllPlayers()  
    {    
        List<Player> playerRecords = new ArrayList<>();    
        playerRepository.findAll().forEach(playerRecords::add);    
        return playerRecords;    
    }

    public Player findByName(String chars){
        return playerRepository.findByName(chars);
    }

    public Optional<Player> getPlayer(int id) {
        return playerRepository.findById(id);
    }

    public int playerExistsByName(String name){
        return this.playerRepository.playerExistsByName(name);
    }

    public String bestScorer(){
        String info = this.playerRepository.bestScorer();
        if(info!=null)
            return info;
        return "---,---";
    }

    public void updatePlayer(Player p){
        this.playerRepository.updatePlayer(p.getName(), p.getPosition(), p.getBirthdate(), p.getTeam(), p.getId());
    }
}