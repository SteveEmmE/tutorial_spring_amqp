package com.example.demo.tut6;

import java.nio.charset.StandardCharsets;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Tut6Receiver {

    @Autowired
    private TopicExchange taskManager;


    @RabbitListener(queues = "#{authRec.name}")
    public void receiveAuth(ResultAuthJson in) throws InterruptedException {
        receiveAuth(in, 1);
    }

    @RabbitListener(queues = "#{taskRec.name}")
    public void receiveTask(ResultTaskJson in) throws InterruptedException {
        receiveTask(in, 2);
    }

    /*
    * Auth
    *   dev --> taskM
    *       { "idDev": "1111", "passwordDev": "1234"}
    * Task
    *   taskM --> dev
    *       { "idDev": "1111", "passwordDev": "1234", "devices":{"dev0": "0000", "dev1":"2222", ...}}
    */
    public void receiveAuth(ResultAuthJson in, int receiver) throws InterruptedException {
        System.out.println("instance " + receiver + " [x] Received '" + in.toString() + "'");
        
        
    }

    public void receiveTask(ResultTaskJson in, int receiver) throws InterruptedException {
        System.out.println("instance " + receiver + " [x] Received '" + in.toString() + "'");
        
        
    }
}