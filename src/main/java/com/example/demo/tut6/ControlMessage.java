package com.example.demo.tut6;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class ControlMessage {
    

    @NonNull
    private String command;
    
}
