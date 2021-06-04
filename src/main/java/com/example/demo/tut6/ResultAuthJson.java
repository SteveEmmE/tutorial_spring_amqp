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
public class ResultAuthJson {

    @NonNull
    private String idDev;

    @NonNull
    private String passwordDev;
}
