package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import java.util.ArrayList;    
import org.springframework.beans.factory.annotation.Autowired;    
import org.springframework.stereotype.Service;

import com.example.data.Users;

@Service    
public class UsersService   
{    
    @Autowired    
    private UsersRepository usersRepository;

    public void addUser(Users users)  {    
        usersRepository.save(users);    
    }

    public Users findByName(String chars){
        return usersRepository.findByName(chars);
    }

    
    public int userExistsByName(String name, String email){
        return this.usersRepository.userExistsByName(name, email);
    }
}