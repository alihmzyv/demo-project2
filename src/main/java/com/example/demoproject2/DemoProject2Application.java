package com.example.demoproject2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class DemoProject2Application {
    public static void main(String[] args) {
        SpringApplication.run(DemoProject2Application.class, args);
    }
}
