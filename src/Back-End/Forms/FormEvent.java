package com.example.formdata;
import com.example.data.Event;
import com.example.data.Player;

public class FormEvent {
    private Event event;
    private Player playerA;
    private Player playerB;

    public FormEvent(){
        this.event = new Event();
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Player getPlayerA() {
        return this.playerA;
    }

    public void setPlayerA(Player playerA) {
        this.playerA = playerA;
    };

    
    public Player getPlayerB() {
        return this.playerB;
    }

    public void setPlayerB(Player playerB) {
        this.playerB = playerB;
    };

}
