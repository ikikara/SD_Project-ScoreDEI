package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import java.sql.Date;
import java.util.ArrayList;    
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;

import com.example.data.Team;
import com.example.formdata.FormTeam;
import com.example.data.Player;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;

@Service    
public class TeamService   
{    
    @Autowired    
    private TeamRepository teamRepository;
    @Autowired
    private PlayerService playerService;

    public int addTeam(FormTeam team)   {  
        Team team_create = new Team();

        try{
            if(team.getImp()){  
                Unirest.setTimeouts(0, 0);
                HttpResponse<JsonNode> response = Unirest.get("https://v3.football.api-sports.io/teams")
                .header("x-rapidapi-key", "ff63ac8a4fa763467c5f73b4bb747473")
                .header("x-rapidapi-host", "v3.football.api-sports.io")
                .queryString("name", team.getName())
                .asJson();

                if(response.getBody().getObject().getInt("results") == 0){
                    return 1;
                }

                JSONObject team_imported = response.getBody().getObject().getJSONArray("response").getJSONObject(0).getJSONObject("team");
                team_create.setName(team_imported.getString("name"));
                team_create.setLogo(team_imported.getString("logo"));
                System.out.println(team_imported.getString("name"));

                HttpResponse<JsonNode> response2 = Unirest.get("https://v3.football.api-sports.io/players")
                .header("x-rapidapi-key", "ff63ac8a4fa763467c5f73b4bb747473")
                .header("x-rapidapi-host", "v3.football.api-sports.io")
                .queryString("team", team_imported.getInt("id"))
                .queryString("season", 2021)
                .asJson();

                teamRepository.save(team_create); 
                JSONArray players = response2.getBody().getObject().getJSONArray("response");
                for(int i=0; i < players.length(); i++){
                    Player player = new Player(
                        players.getJSONObject(i).getJSONObject("player").getString("name"),
                        players.getJSONObject(i).getJSONArray("statistics").getJSONObject(0).getJSONObject("games").getString("position"),
                        Date.valueOf(players.getJSONObject(i).getJSONObject("player").getJSONObject("birth").getString("date")),
                        team_create);
                    playerService.addPlayer(player);
                }
            }
            else{
                team_create.setName(team.getName());
                teamRepository.save(team_create);
            }
        } 
        catch (Exception e){
            System.out.println(e);
			System.out.println("Error!\n");
		}
        
        return 0;
    }

    public Team findByName(String chars){
        return teamRepository.findByName(chars);
    }

    public List<Team> getAllTeams()  {    
        List<Team> allteams = new ArrayList<>();    
        teamRepository.findAll().forEach(allteams::add);    
        return allteams;    
    }
    
    public int teamExistsByName(String name){
        return this.teamRepository.teamExistsByName(name);
    }

    public void setTeamVictories(int numV, int id){
        this.teamRepository.setTeamVictories(numV, id);
    }

    public void setTeamDefeats(int numD, int id){
        this.teamRepository.setTeamDefeats(numD, id);   
    }

    public void setTeamTies(int numT, int id){
        this.teamRepository.setTeamTies(numT, id);
    }

    public void setTeamGames(int numG, int id){
        this.teamRepository.setTeamGames(numG, id);
    }

    public void updatePlayer(String name, String logo, int id){
        this.teamRepository.updateTeam(name, logo, id);
    }
}