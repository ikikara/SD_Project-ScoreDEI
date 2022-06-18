package com.example.demo;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.example.data.Team;
import com.example.data.Player;
import com.example.data.Users;
import com.example.data.Game;
import com.example.data.Event;
import com.example.data.UserLogged;
import com.example.formdata.FormData;
import com.example.formdata.FormEvent;
import com.example.formdata.FormPlayer;
import com.example.formdata.FormTeam;
import com.example.formdata.FormTeamVsTeam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class DataController {
    @Autowired
    TeamService teamService;

    @Autowired
    PlayerService playerService;

    @Autowired
    UsersService usersService;

    @Autowired
    GameService gameService;

    @Autowired
    EventService eventService;

    UserLogged UserLogged = new UserLogged();

    public boolean adminhelp(Model m, boolean perms){
        if (UserLogged.isAvailable()){
            if (UserLogged.getUser().getAdmin()){
                m.addAttribute("admin", "1");
            } else {
                if (!perms)
                    m.addAttribute("admin", "0");
                else
                    return false;
            }
            m.addAttribute("user", "1"); 
        } else {
            if (!perms)
                m.addAttribute("user", "0");
            else
                return false;
        }
        return true;
    }

    @GetMapping("/")
    public String redirect() {
        try{
            Users found = this.usersService.findByName("Admin1");
            if (found == null){
                Users admin = new Users("Admin1", "admin@dei.uc.pt", "1234", "918394627", true);
            	this.usersService.addUser(admin);
            }
        } catch (Exception e){
            return "redirect:/start";
        }
        return "redirect:/start";
    }

    @GetMapping("/start")
    public String start(Model m){
        adminhelp(m, false);
        return "start";
    }

    @GetMapping("/admin")
    public String admin(Model m){

        if (UserLogged.isAvailable()){
            if (UserLogged.getUser().getAdmin()){
                m.addAttribute("player", new FormPlayer());
                m.addAttribute("team", new Team());
                return "admin";
            }
        }
        // Dar display a uma mensagem tipo "Não tem permissoes para aceder a esta pagina"
        return "redirect:/start";
    }

    @GetMapping("/profile")
    public String profile(Model m){
        if (UserLogged.isAvailable()){
            m.addAttribute("user", UserLogged.getUser());
            return "profile";
        } else {
            return "redirect:/start";
        }
    }

    @GetMapping("/auth")
    public String auth(@ModelAttribute("error") String error, Model m) {
        if(!adminhelp(m, true)){
            return "redirect:/start";
        }
        m.addAttribute("users", new Users());
        if(error.compareTo("1") == 0){
            m.addAttribute("error", "1");
        }
        return "auth";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute Users us){
        if(this.usersService.userExistsByName(us.getName(), us.getEmail()) == 0){
            this.usersService.addUser(us);
            return "redirect:/admin";
        }

        return "redirect:/auth?error=1";
    }

    @GetMapping("/addPlayer")
    public String addPlayer(@ModelAttribute("error") String error, Model m) {
        if(!adminhelp(m, true)){
            return "redirect:/start";
        }
        m.addAttribute("player", new FormPlayer());
        m.addAttribute("update", false);
        m.addAttribute("allTeams", teamService.getAllTeams());
        if(error.compareTo("1") == 0){
            m.addAttribute("error", "1");
        }
        return "addplayer";
    }

    @PostMapping("/editPlayer")
    public String editPlayer(@ModelAttribute FormPlayer player, Model m) {
        try{
            Player found = this.playerService.findByName(player.getPlayer().getName());
            if (found != null){
                m.addAttribute("update", true);
                player.setPlayer(found);
                m.addAttribute("player", player);
                m.addAttribute("allTeams", this.teamService.getAllTeams());
                return "addplayer";
            } else {
                return "redirect:/admin";
            }
        } catch (Exception e){
            return "redirect:/admin";
        }    
    }

    @PostMapping("/savePlayer")
    public String savePlayer(@ModelAttribute FormPlayer player){
        if(player.getUpdate() == true){
            this.playerService.updatePlayer(player.getPlayer());
            return "redirect:/admin";
        }

        if(this.playerService.playerExistsByName(player.getPlayer().getName())== 0){
            
            this.playerService.addPlayer(player.getPlayer());
            return "redirect:/admin";
        }
        return "redirect:/addPlayer?error=1";
    }

    @GetMapping("/addTeam")
    public String addTeam(@ModelAttribute("error") String error, Model m) {
        if(!adminhelp(m, true)){
            return "redirect:/start";
        }
        m.addAttribute("team", new FormTeam());
        m.addAttribute("allPlayers", this.playerService.getAllPlayers());
        m.addAttribute("update", false);
        if(error.compareTo("1") == 0){
            m.addAttribute("error", "1");
        }
        return "addteam";
    }

    @PostMapping("/editTeam")
    public String editTeam(@ModelAttribute Team team, Model m) {
        if(!adminhelp(m, true)){
            return "redirect:/start";
        }

        FormTeam ft = new FormTeam();

        try{
            Team found = this.teamService.findByName(team.getName());
            if (found != null){
                ft.setName(team.getName());
                ft.setLogo(team.getLogo());
                
                m.addAttribute("id", found.getId());
                m.addAttribute("team", ft);        
                m.addAttribute("update", true);
                return "addteam";
            } else {
                return "redirect:/admin";
            }
        } catch (Exception e){
            return "redirect:/admin";
        }    
    }

    @PostMapping("/saveTeam")
    public String saveTeam(@ModelAttribute FormTeam FormTeam){
        if(FormTeam.getUpdate() == true){
            this.teamService.updatePlayer(FormTeam.getName(), FormTeam.getLogo(), FormTeam.getId());
            return "redirect:/admin";
        }

        if(this.teamService.teamExistsByName(FormTeam.getName()) == 0){
            if(this.teamService.addTeam(FormTeam) == 0){
                return "redirect:/admin";
            }

            return "redirect:/addTeam?error=2";
        }

        return "redirect:/addTeam?error=1";
    }

    @GetMapping("/addEvent")
    public String addEvent(@RequestParam(name="id", required=true) int id, @RequestParam(name="error", required=false) String error, Model m){
        Optional<Game> op = this.gameService.getGame(id);
        if (op.isPresent()) {
            m.addAttribute("event", new FormEvent());
            m.addAttribute("game", op.get());
            m.addAttribute("error", error);
            return "editevent";
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/saveEvent")
    public String saveEvent(@ModelAttribute FormEvent event){
        if(event.getPlayerA() == null){
            event.getEvent().setPlayer(event.getPlayerB());
        }
        if(event.getPlayerB() == null){
            event.getEvent().setPlayer(event.getPlayerA());
        }

        int result = this.eventService.addEvent(event.getEvent()); 

        if(result == 0){
            return "redirect:/listGames";
        }

        return "redirect:/addEvent?id="+event.getEvent().getGame().getId()+"&error="+result;
    }

    @GetMapping("/validEvents")
    public String validEvents(@RequestParam(name="error", required=false) String error, Model m){
        if(!adminhelp(m, true)){
            return "redirect:/start";
        }
        List<Event> events = eventService.findToValidEventsById();

        m.addAttribute("valids", new FormData());
        m.addAttribute("events", events);
        m.addAttribute("error", error); 

        return "validEvents";
    }

    @PostMapping("/saveValid")
    public String saveValid(@ModelAttribute FormData valids ){
        int result = this.eventService.setEventValidation(valids.getValid(), valids.getEvent());

        if(result == 10){      
            if(valids.getEvent().getType().compareTo("End the Game")==0){
                if(gameService.goalsByTeamInAGame(valids.getEvent().getGame(), valids.getEvent().getGame().getTeamA()) == gameService.goalsByTeamInAGame(valids.getEvent().getGame(), valids.getEvent().getGame().getTeamB())){
                    this.teamService.setTeamTies(1, valids.getEvent().getGame().getTeamA().getId());
                    this.teamService.setTeamTies(1, valids.getEvent().getGame().getTeamB().getId());
                }
                else if(gameService.goalsByTeamInAGame(valids.getEvent().getGame(), valids.getEvent().getGame().getTeamA()) > gameService.goalsByTeamInAGame(valids.getEvent().getGame(), valids.getEvent().getGame().getTeamB())){
                    this.teamService.setTeamVictories(1, valids.getEvent().getGame().getTeamA().getId());
                    this.teamService.setTeamDefeats(1, valids.getEvent().getGame().getTeamB().getId());
                }
                else{
                    this.teamService.setTeamVictories(1, valids.getEvent().getGame().getTeamB().getId());
                    this.teamService.setTeamDefeats(1, valids.getEvent().getGame().getTeamA().getId());
                }

                this.teamService.setTeamGames(1, valids.getEvent().getGame().getTeamA().getId());
                this.teamService.setTeamGames(1, valids.getEvent().getGame().getTeamB().getId());
            }
            
            return "redirect:/admin";
        }

        return "redirect:/validEvents?error="+result;
    }

    @GetMapping("/addGame")
    public String addGame(Model m) {
        if(!adminhelp(m, true)){
            return "redirect:/start";
        }
        m.addAttribute("game", new Game());
        m.addAttribute("allTeams", this.teamService.getAllTeams());
        return "addGame";
    }

    @PostMapping("/saveGame")
    public String saveGame(@ModelAttribute Game game){
        game.setStatus("Not Started");
        this.gameService.addGame(game);
        return "redirect:/admin";
    }


    @GetMapping("/listGames")
    public String listGames(Model m){
        List<Game> games = this.gameService.getAllGames();
        m.addAttribute("games", games);

        List<Integer> goalsA = new ArrayList<Integer>();
        List<Integer> goalsB = new ArrayList<Integer>();
        for(int i=0; i<games.size(); i++){
            goalsA.add(this.gameService.goalsByTeamInAGame(games.get(i), games.get(i).getTeamA()));
            goalsB.add(this.gameService.goalsByTeamInAGame(games.get(i), games.get(i).getTeamB()));
        }
        m.addAttribute("goalsA", goalsA);
        m.addAttribute("goalsB", goalsB);

        if (UserLogged.isAvailable()){ 
            m.addAttribute("user", "1"); 
        } else {
            m.addAttribute("user", "0");
        }   

        return "listGames";
    }

    @GetMapping("/listEvents")
    public String listEvents(@RequestParam(name="id", required=true) int id, Model m){
        Optional<Game> op = this.gameService.getGame(id);
        if (op.isPresent()) {
            Game game = op.get();
            m.addAttribute("events",  eventService.findValidEventsById(id));
            m.addAttribute("teamA", game.getTeamA());
            m.addAttribute("teamB", game.getTeamB());
            m.addAttribute("goalsA", this.gameService.goalsByTeamInAGame(game, game.getTeamA()));
            m.addAttribute("goalsB", this.gameService.goalsByTeamInAGame(game, game.getTeamB()));
            
            int lA = game.getTeamA().getPlayers().size();
            int lB = game.getTeamB().getPlayers().size();
            if(lA > lB){
                m.addAttribute("length", lA);
            }
            else{
                m.addAttribute("length", lB);
            }

            
            if (UserLogged.isAvailable()){
                m.addAttribute("user", "1");
            } else {
                m.addAttribute("user", "0");
            }

            return "listevents";
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/check")
    public String check(@ModelAttribute Users us, RedirectAttributes m){
        try{
            Users found = this.usersService.findByName(us.getName());
            if (found.getPassword().equals(us.getPassword())){
                UserLogged.setUser(found);
                if (found.getAdmin()){
                    return "redirect:/admin";
                } else {
                    return "redirect:/start";
                }    
            } else {
                m.addAttribute("error", "1");
                return "redirect:/login";
            }
        } catch (Exception e){
            m.addAttribute("error", "2");
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("error") String error, Model m) {
        if(m.getAttribute("error").toString().compareTo("1") == 0){
            m.addAttribute("error", "1");
        }
        else if(m.getAttribute("error").toString().compareTo("2") == 0){
            m.addAttribute("error", "2");
        }
        else{
            m.addAttribute("error", "0");
        }
        m.addAttribute("users", new Users());
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        UserLogged.logout();
        return "redirect:/start";
    }

    @GetMapping("/statistics")
    public String statistics(Model m){
        List<Team> teams = this.teamService.getAllTeams();
        m.addAttribute("teams", teams);

        if (UserLogged.isAvailable()){
            m.addAttribute("user", "1");
        } else {
            m.addAttribute("user", "0");
        }

        String[] info = playerService.bestScorer().split(",");

        List<Game> games = this.gameService.getAllGames();
        int max = 0, aux = 0;
        for(int i=0; i<games.size(); i++){
            aux += this.gameService.goalsByTeamInAGame(games.get(i), games.get(i).getTeamA());
            aux += this.gameService.goalsByTeamInAGame(games.get(i), games.get(i).getTeamB());
            if(aux>max)
                max = aux;
            aux = 0;
        }

        m.addAttribute("scorer", info[0]);
        m.addAttribute("goals", info[1]);
        m.addAttribute("max_goals", max);
        m.addAttribute("average_goals", (float)(this.eventService.numberGoals())/(float)(this.gameService.numberGames()));
        m.addAttribute("teamvsteam", new FormTeamVsTeam());

        return "statistics";
    }



    @GetMapping("/teamvsteam")
    public String teamVSteam(@ModelAttribute FormTeamVsTeam tvst, Model m){
        if (UserLogged.isAvailable()){
            m.addAttribute("user", "1");
        } 
        else {
            m.addAttribute("user", "0");
        }
        
        Team tA = this.teamService.findByName(tvst.getIdA()); 
        Team tB = this.teamService.findByName(tvst.getIdB()); 
        int nVA = 0; int nVB = 0; int nT = 0;
        int nYC = 0; int nRC = 0;
        int nrGames = 0;

        for(Game g: this.gameService.gamesBetweenTeams(this.teamService.findByName(tvst.getIdA()), this.teamService.findByName(tvst.getIdB()))){
            if(this.gameService.goalsByTeamInAGame(g, tA) > this.gameService.goalsByTeamInAGame(g, tB)){
                nVA++;
            }
            else if(this.gameService.goalsByTeamInAGame(g, tA) == this.gameService.goalsByTeamInAGame(g, tB)){
                nT++;
            }
            else{
                nVB++;
            }
            nYC += this.gameService.yellowCardByTeamInAGame(g, tA) + this.gameService.yellowCardByTeamInAGame(g, tB);
            nRC += this.gameService.redCardByTeamInAGame(g, tA) + this.gameService.redCardByTeamInAGame(g, tB);
            nrGames++;
        }

        m.addAttribute("teamA", tvst.getIdA());
        m.addAttribute("teamB", tvst.getIdB());
        m.addAttribute("nr_games", nrGames);
        m.addAttribute("nVA", nVA);
        m.addAttribute("nVB", nVB);
        m.addAttribute("nT", nT);
        m.addAttribute("nYC", nYC);
        m.addAttribute("nRC", nRC);

        return "teamvsteam";
    }
}
