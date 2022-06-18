package com.example.data;

import java.util.List;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Users{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name, email, password, cellphone;
    private boolean admin;

    public Users() {}

    public Users(String name, String email, String password, String cellphone, boolean admin){
        this.name = name;
        this.email = email;
        this.password = password;
        this.cellphone = cellphone;
        this.admin = admin;
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

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getCellphone(){
        return cellphone;
    }

    public void setCellphone(String cellphone){
        this.cellphone = cellphone;
    }

    public boolean getAdmin(){
        return this.admin;
    }

    public void setAdmin(boolean admin){
        this.admin = admin;
    }
}