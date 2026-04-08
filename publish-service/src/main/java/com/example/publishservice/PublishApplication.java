package com.example.publishservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PublishApplication {
    public static void main(String[] args) {
        SpringApplication.run(PublishApplication.class, args);
    }
}
