package com.example.demo.tut6;


import javax.annotation.Resource;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageManager {

    @Resource
    RabbitAdmin rabbitAdmin;

    @Resource
    private RabbitTemplate rabbitTemplate;

    private String taskName = "TaskCar";

    @Autowired
    private TopicExchange taskManager;

    @Autowired
    private Queue task;

    @Autowired
    private Queue auth;


 // Creating Dynamic Queues and Switches


    /**
     * Send delayed message
     * @param object
     * @param delayTime
     */
    public void bindingTask( String idDev, String message )  throws AmqpException {
        System.out.println("Sender");
        String routingKey =  taskName + ".control.task."+ idDev; 
        addBinding(task, taskManager, routingKey);

        rabbitTemplate.convertAndSend(taskManager.getName(), routingKey , message.getBytes());
        System.out.println(message);
    }

    public void bindingAuth( String idDev, String message )  throws AmqpException {
        System.out.println("Sender");
        String routingKey =  taskName + ".control.auth" + idDev; 
        addBinding(auth, taskManager, routingKey);

        rabbitTemplate.convertAndSend(taskManager.getName(), routingKey , message.getBytes());
        System.out.println(message);
    }

    /**
           * Bind a queue to a matching switch using a routingKey
     *
     * @param queue
     * @param exchange
     * @param routingKey
     */
    private void addBinding(Queue queue, TopicExchange exchange, String routingKey) {
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);
        rabbitAdmin.declareBinding(binding);
    }

    /*public void createQueueAndListen(String queueName) {
        System.out.println("Listener");
        addBinding(queue, topic, "*.mario");
        System.out.println("QUI");


        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
        container.setMessageListener(new MessageListener(){

            @Override
            public void onMessage(Message message) {
                System.out.println("Consuming Message - " + new String(message.getBody()));
            }
            
        }); /* {

            public void handleMessage(byte[] rawData) {
                String message = new String(rawData, StandardCharsets.UTF_8);
                System.out.println(message.toString());
                container.destroy();
                System.out.println("ehhmm dovrei aver stoppato il listener... però controlla bene..");
            }
        }));
        container.start();*/

        

        


       /*  byte[] rawData = (byte[]) rabbitTemplate.receiveAndConvert(queueName);
        System.out.println(new String(rawData, StandardCharsets.UTF_8)); 
    }*/


    

}