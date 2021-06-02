package com.example.demo.tut6;


import java.util.HashMap;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Tut6Config {

    private String taskName = "TaskCar";

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost; 
    
    @Bean("mqConnectionFactory")
    public ConnectionFactory mqConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitHost);

        return connectionFactory;
    }

    @Bean
    public HashMap<String, Queue> idsQueue(){
        return new HashMap<String, Queue>();
    }

    @Bean
    public HashMap<String, Binding> idsBinding(){
        return new HashMap<String, Binding>();
    }

    @Bean
    public RabbitAdmin rabbitAdmin( ) {
        return new RabbitAdmin(mqConnectionFactory());
    }

    @Bean
    public TopicExchange taskManager() {
        return new TopicExchange("TaskManager");
    }

    @Bean
    public Queue authRec() {
        return new Queue("auth");
    }

    @Bean
    public Queue taskRec() {
        return new Queue("task");
    }
    
    @Bean
    public Binding authQueueBinding(TopicExchange taskManager,
        Queue authRec) {
        return BindingBuilder.bind(authRec)
            .to(taskManager)
            .with(taskName + ".result.auth.*");
    }

    @Bean
    public Binding taskQueueBinding(TopicExchange taskManager,
        Queue taskRec) {
        return BindingBuilder.bind(taskRec)
            .to(taskManager)
            .with(taskName + ".result.task.*");
    }
}

    