package com.example.demo.tut6;



import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageManager {

    @Resource
    RabbitAdmin rabbitAdmin;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange topic;

    @Autowired
    private ConnectionFactory connectionFactory;
    


 // Creating Dynamic Queues and Switches


    /**
     * Send delayed message
     * @param object
     * @param delayTime
     */
    public void createQueueAndSend( String message)  throws AmqpException {
        System.out.println("Sender");
        String queueName = "mario";
        Queue queue = new Queue(queueName);
        addQueue(queue);
        addBinding(queue, topic, "*."+queueName);

        rabbitTemplate.convertAndSend(topic.getName(), "saluti.mario" , message.getBytes());
        System.out.println(message);
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

    public void createQueueAndListen(String queueName) {
        System.out.println("Listener");
        Queue queue = new Queue(queueName);
        addQueue(queue);
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
               
                container.stop();
            }
            
        }); /* {

            public void handleMessage(byte[] rawData) {
                String message = new String(rawData, StandardCharsets.UTF_8);
                System.out.println(message.toString());
                container.destroy();
                System.out.println("ehhmm dovrei aver stoppato il listener... però controlla bene..");
            }
        })); */
        container.start();

        

        


       /*  byte[] rawData = (byte[]) rabbitTemplate.receiveAndConvert(queueName);
        System.out.println(new String(rawData, StandardCharsets.UTF_8)); */
    }

    

}