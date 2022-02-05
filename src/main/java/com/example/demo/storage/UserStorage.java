package com.example.demo.storage;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserStorage {
    private static Set<String> users;

    private UserStorage(){
        this.users = new HashSet<String>();
    }

    public static Set<String> getUsers(){
        return users;
    }

    public void setUser(String userName) throws Exception {
        if(users.contains( userName)){
            throw new Exception("User exists! : " + userName);
        }
        System.out.println(users);
        users.add(userName);

    }
}
