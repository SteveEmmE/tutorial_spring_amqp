package com.example.demo.tut6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private Tut6Receiver receiver;

    @GetMapping("/api/listener")
    public void listen() {
        receiver.receive();
    }

}