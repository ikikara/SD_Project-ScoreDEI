package com.example.demo;

import java.util.*;

import javax.transaction.Transactional;
   
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;


import com.example.data.Event;
import com.example.data.Game;

@Service    
public class EventService   
{    
    @Autowired    
    private EventRepository eventRepository;

    //meter a retornar numeros para diferenciar os diferentes erros
    public int addEvent(Event event)  {    
        Game game = eventRepository.findGameById(event.getGame().getId());
        if(game.getStatus().compareTo("Finished") == 0){
            return 5;
        }

        if(game.getStatus().compareTo("Not Started") == 0){
            if(event.getType().compareTo("Start the Game") != 0){
                return 1;
            }
        }
        else{
            if(event.getType().compareTo("Resume the Game") == 0){
                if(game.getStatus().compareTo("Resumed") == 0){
                    return 2;
                }
            }
            else if(event.getType().compareTo("Pause the Game") == 0){
                if(game.getStatus().compareTo("Paused") == 0){
                    return 3;
                }
            }
            else if(event.getType().compareTo("Start the Game") == 0){
                return 6;
            }
            else{
                if(game.getStatus().compareTo("Paused") == 0 && event.getType().compareTo("End the Game") != 0){
                    return 4;
                }
                else{
                    if(eventRepository.nrRedCardsPlayerGame(event.getPlayer(), event.getGame()) > 0 || eventRepository.nrYellowCardsPlayerGame(event.getPlayer(), event.getGame()) > 1){
                            return 7;
                    }
                }
            }
        }

 
        eventRepository.save(event);    
        return 0;
    }

    public int setEventValidation(int valid, Event event){
        Game game = event.getGame();

        if(valid == 2){
            
            this.eventRepository.deleteEventById(event.getId());
            return 10;
        }

        if(event.getType().compareTo("Start the Game") == 0){
            if(game.getStatus().compareTo("Resumed") == 0){            
                return 11;
            }
            eventRepository.setGameStatus("Resumed", game.getId());
        }
        else if(event.getType().compareTo("End the Game") == 0){
            if(game.getStatus().compareTo("Finished") == 0){            
                return 12;
            }
            eventRepository.setGameStatus("Finished", game.getId());
        }
        else if(event.getType().compareTo("Resume the Game") == 0){
            if(game.getStatus().compareTo("Resumed") == 0){            
                return 13;
            }
            eventRepository.setGameStatus("Resumed", game.getId());
        }
        else if(event.getType().compareTo("Pause the Game") == 0){
            if(game.getStatus().compareTo("Paused") == 0){            
                return 14;
            } 
            eventRepository.setGameStatus("Paused", game.getId());
        }
        else{
            if(game.getStatus().compareTo("Paused") == 0 || game.getStatus().compareTo("Finished") == 0){
                return 15;
            }
        }

        this.eventRepository.setEventValidation(valid, event.getId());
        return 10;
    }

    public List<Event> findValidEventsById(int id){
        return this.eventRepository.findValidEventsById(this.eventRepository.findGameById(id));
    }

    public List<Event> findToValidEventsById(){
        return this.eventRepository.findToValidEventsById();
    }

    public int numberGoals(){
        return this.eventRepository.numberGoals();
    }

}