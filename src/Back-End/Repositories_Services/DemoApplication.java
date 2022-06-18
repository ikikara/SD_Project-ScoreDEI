package com.example.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.URLEncoder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@SpringBootApplication
@EnableJpaRepositories("com.example.*")
@EntityScan("com.example.*")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		
		/*try{
			Unirest.setTimeouts(0, 0);
			HttpResponse<JsonNode> response = Unirest.get("https://v3.football.api-sports.io/teams")
			.header("x-rapidapi-key", "ff63ac8a4fa763467c5f73b4bb747473")
			.header("x-rapidapi-host", "v3.football.api-sports.io")
			.queryString("name", "Manchester City")
			.asJson();
			
				System.out.println(response.getStatus());
				System.out.println(response.getHeaders().get("Content-Type"));
			
			//System.out.println(response.getBody().getObject().getInt("results")); 
			JSONObject team = response.getBody().getObject().getJSONArray("response").getJSONObject(0).getJSONObject("team");
			//System.out.println(team);

			HttpResponse<JsonNode> response2 = Unirest.get("https://v3.football.api-sports.io/players")
			.header("x-rapidapi-key", "ff63ac8a4fa763467c5f73b4bb747473")
			.header("x-rapidapi-host", "v3.football.api-sports.io")
			.queryString("team", team.getInt("id"))
			.queryString("season", 2021)
			.asJson();

			JSONArray players = response2.getBody().getObject().getJSONArray("response");
			for(int i=0; i < players.length(); i++){
				System.out.println(players.getJSONObject(i).getJSONObject("player").getString("name"));
			}

			//System.out.println(response2.getBody().getObject());
		} catch (Exception e){
			System.out.println("Error!\n");
		}*/
		
	}

}
