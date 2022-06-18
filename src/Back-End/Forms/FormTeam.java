package com.example.formdata;

import com.example.data.Team;

public class FormTeam {
    private String name;
    private boolean imp;
    private String logo;
    private boolean update;
    private int id;

    public FormTeam(){
        
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getImp(){
        return this.imp;
    }

    public void setImp(Boolean imp){
        this.imp = imp;
    }

    public String getLogo(){
        return this.logo;
    }

    public void setLogo(String logo){
        this.logo = logo;
    }

    public boolean getUpdate(){
        return this.update;
    }

    public void setUpdate(boolean update){
        this.update = update;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }
}