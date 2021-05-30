package com.example.demo.tut6;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Tut6Sender {

    public static int i = 2;

    @Autowired
    private MessageManager messageManager;
 
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        messageManager.createQueueAndSend((i++)+" saluti per mario");
    }
}
