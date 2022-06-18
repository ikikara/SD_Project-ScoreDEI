package com.example.formdata;
import com.example.data.Player;

public class FormPlayer {
    private Player player;
    boolean update;

    public FormPlayer(){
        this.player = new Player();
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean getUpdate(){
        return this.update;
    }

    public void setUpdate(boolean update){
        this.update = update;
    }

}
