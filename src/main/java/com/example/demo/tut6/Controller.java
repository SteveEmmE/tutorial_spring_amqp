package com.example.demo.tut6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

   /*  @Autowired
    private Tut6Receiver receiver; */

    @Autowired
    private Tut6Sender sender;


    /*@GetMapping("/listener")
    public void listenAuth() {
        //receiver.receiveAuth("1111","message");
    }*/

   /*  @GetMapping("/sender/auth")
    public void sendAuth() {
        sender.sendAuth("1111", "meaaass");
    } */

    /*@GetMapping("/listener/task")
    public void listenTask() {
        receiver.receive();
    }*/

    @PostMapping("/sender/task/{deviceId}")
    public void sendTask(@PathVariable String deviceId, @RequestBody ControlMessage message) {
        sender.sendTask(deviceId, message);
    }
}