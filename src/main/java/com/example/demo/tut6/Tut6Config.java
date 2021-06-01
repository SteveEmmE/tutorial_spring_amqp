package com.example.demo.tut6;


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
    public RabbitAdmin rabbitAdmin( ) {
        return new RabbitAdmin(mqConnectionFactory());
    }

    @Bean
    public TopicExchange taskManager() {
        return new TopicExchange("TaskManager");
    }

    @Bean
    public Queue auth() {
        return new Queue("auth");
    }

    @Bean
    public Queue task() {
        return new Queue("task");
    }

    @Bean
    public Binding taskListener(TopicExchange taskManager,
        Queue task) {
        return BindingBuilder.bind(task)
            .to(taskManager)
            .with(taskName + ".result.task.*");
    }

    @Bean
    public Binding authListener(TopicExchange taskManager,
        Queue auth) {
        return BindingBuilder.bind(auth)
            .to(taskManager)
            .with(taskName + ".result.auth.*");
    }
}