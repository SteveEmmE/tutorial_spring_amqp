package com.example.demo.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile({"tut1","hello-world"})
@Configuration
public class Tut1Config {

    @Bean("hello")
    public Queue hello() {
        return new Queue("hello");
    }

    @Bean("helloBelin")
    public Queue helloBelin() {
        return new Queue("helloBelin");
    }

    @Profile("receiver")
    @Bean
    public Tut1Receiver receiver() {
        return new Tut1Receiver();
    }

    @Profile("sender")
    @Bean
    public Tut1Sender sender() {
        return new Tut1Sender();
    }
}