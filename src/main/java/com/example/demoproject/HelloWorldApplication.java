package com.example.demoproject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class HelloWorldApplication {
    @GetMapping("/")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/date")
    public String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(new Date());
    }

}


