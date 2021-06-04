package com.example.demo.tut6;


import java.util.HashMap;

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

    private String taskName = "CarTaskManager";

    @Resource
    RabbitAdmin rabbitAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange taskManager;

    @Autowired
    private HashMap<String, Queue> idsQueue;

    @Autowired
    private HashMap<String, Binding> idsBinding;

    @Autowired
    private ControlMessage controlMessage;


 // Creating Dynamic Queues and Switches


    /**
     * Send delayed message
     */
    public void bindingControl( String idDev, ControlMessage message )  throws AmqpException {
        System.out.println("Sender");
        String routingKey;
        if(message.getCommand().equals("Authenticate"))
            routingKey =  /* taskName + ". */"control.auth."+ idDev; 
        else
            routingKey =  /* taskName + ". */"control.task."+ idDev; 
        if(!idsQueue.containsKey(idDev)){
            idsQueue.put(idDev, new Queue(idDev));
            addQueue(idsQueue.get(idDev));
            addBinding(idsQueue.get(idDev), taskManager, routingKey);
        }


        rabbitTemplate.convertAndSend(taskManager.getName(), routingKey, message);
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
        idsBinding.put(queue.getName(),BindingBuilder.bind(queue).to(exchange).with(routingKey));
        rabbitAdmin.declareBinding(idsBinding.get(queue.getName()));
    }

    /**
     * Create a specified Queue
     *
     * @param queue
     * @return queueName
     */
    private String addQueue(Queue queue) {
        return rabbitAdmin.declareQueue(queue);
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