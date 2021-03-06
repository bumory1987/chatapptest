package com.example.demo.controller;


import com.example.demo.storage.UserStorage;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin("*")
@RestController
public class UserController {

    @Autowired
    UserStorage userStorage;

    @GetMapping("/registration/{username}")
    public ResponseEntity<Void> register(@PathVariable String username){
        System.out.println("handling register user request" + username);
        try{
            userStorage.setUser(username);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            System.out.println("e.getMessage() = " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/delete")
    public ResponseEntity<Void> deleteall(){
        UserStorage.delete();
        return ResponseEntity.ok().build();
    }



    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll(){
        return UserStorage.getUsers();
    }

}
