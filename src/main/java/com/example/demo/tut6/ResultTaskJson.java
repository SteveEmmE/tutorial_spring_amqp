package com.example.demo.tut6;

import java.util.ArrayList;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

//{ "idDev": "1111", "passwordDev": "1234", "devices":["0000", "2222"]}

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class ResultTaskJson {

    @NonNull
    private String idDev;

    @NonNull
    private String passwordDev;

    @NonNull
    private ArrayList<String> devices;

}
