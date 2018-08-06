package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebServiceApplication {
//访问http://localhost:8081/soap
    public static void main(String[] args) {
        SpringApplication.run(WebServiceApplication.class, args);
    }
}
