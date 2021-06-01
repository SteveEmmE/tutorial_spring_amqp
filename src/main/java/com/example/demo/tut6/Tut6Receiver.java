package com.example.demo.tut6;

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


    @RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "auth"),
        exchange = @Exchange(value = "#{taskManager.getName()}"),
        key = "TaskCar.result.auth.*")
    )
    public void receiveAuth(String in) throws InterruptedException {
        receive(in, 1);
    }

    @RabbitListener(queues = "#{task.name}")
    public void receiveTask(String in) throws InterruptedException {
        receive(in, 2);
    }

    public void receive(String in, int receiver) throws
        InterruptedException {

        System.out.println("instance " + receiver + " [x] Received '"
            + in + "'");

    }


}