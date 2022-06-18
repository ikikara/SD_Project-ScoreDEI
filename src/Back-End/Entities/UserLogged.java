package com.example.data;

public class UserLogged {

    private  boolean available;
    private  Users user;

    public UserLogged(){
        this.available = false;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setUser(Users user) {
        this.user = user;
        this.available = true;
    }

    public Users getUser() {
        return this.user;
    }

    public void logout(){
        this.available = false;
    }
}