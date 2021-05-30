package com.example.demo.tut6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Tut6Receiver {

    public static int i = 2;

    @Autowired
    private MessageManager messageManager;
 
    
    public void receive() {
        messageManager.createQueueAndListen("coda1");
    }
}