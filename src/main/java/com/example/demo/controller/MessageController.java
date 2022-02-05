package com.example.demo.controller;


import com.example.demo.model.MessageModel;
import com.example.demo.storage.UserStorage;
import jdk.jfr.MemoryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:50611/*")
@RestController
public class MessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @CrossOrigin("*")
    @MessageMapping("chat/{to}")
    public void sendMessage(@DestinationVariable String to , MessageModel message){
        System.out.println("handling send message" + message + " to : " + to);
        boolean isExist = UserStorage.getUsers().contains(to);
        if(isExist){
            simpMessagingTemplate.convertAndSend("/topic/message/" + to , message);
        }
    }

}
