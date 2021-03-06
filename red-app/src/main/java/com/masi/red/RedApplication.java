package com.masi.red;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.masi.red")
public class RedApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedApplication.class, args);
    }

}
