package com.example.demo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.data.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {
    @Query("select u from Users u where u.name like %?1")
    public Users findByName(String chars);

    @Query("select count(u)" +
           " from Users u" +
           " where u.name = ?1" +
           " or u.email = ?2")
    public int userExistsByName(String name, String email);
} 