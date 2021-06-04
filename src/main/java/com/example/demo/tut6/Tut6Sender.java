package com.example.demo.tut6;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Tut6Sender {

    public static int i = 2;

    @Autowired
    private MessageManager messageManager;
 
    public void sendAuth( String idDev, ControlMessage message ) {
        messageManager.bindingControl(idDev, message);
    }

    public void sendTask( String idDev, ControlMessage message ) {
        messageManager.bindingControl(idDev, message);
    }
}
