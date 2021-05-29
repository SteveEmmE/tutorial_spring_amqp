package com.example.demo.tut1;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut1Sender {

    public static int i = 0;

    @Autowired
    private RabbitTemplate template;
    
    @Autowired
    @Qualifier("helloBelin")
    private Queue helloB;

    @Autowired
    @Qualifier("hello")
    private Queue hello;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String message = "Hello Bernrdo!"+i;
        template.convertAndSend(helloB.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
        i++;
    }
}
