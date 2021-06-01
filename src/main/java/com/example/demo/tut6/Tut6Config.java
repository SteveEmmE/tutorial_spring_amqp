package com.example.demo.tut6;


import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Tut6Config {


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
    public TopicExchange topic() {
        return new TopicExchange("carTaskExchange");
    }



}