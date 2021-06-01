package com.example.demo.tut6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private Tut6Receiver receiver;

    @Autowired
    private Tut6Sender sender;


    @GetMapping("/listener")
    public void listen() {
        receiver.receive();
    }

    @GetMapping("/sender")
    public void send() {
        sender.send();
    }

}