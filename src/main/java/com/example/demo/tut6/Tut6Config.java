package com.example.demo.tut6;

import lombok.Data;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
@Configuration
public class Tut6Config {

    String host;

    @Bean("mqConnectionFactory")
    public ConnectionFactory mqConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

        // This method configures multiple hosts, when the current connection host down, it will automatically reconnect to the back host
        connectionFactory.setAddresses(host);
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

    @Profile("receiver")
    @Bean
    public Tut6Receiver receiver() {
        return new Tut6Receiver();
    }

    @Profile("sender")
    @Bean
    public Tut6Sender sender() {
        return new Tut6Sender();
    }
}