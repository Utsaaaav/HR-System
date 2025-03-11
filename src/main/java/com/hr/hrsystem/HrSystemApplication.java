package com.hr.hrsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
        //(exclude = {SecurityAutoConfiguration.class})
public class HrSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrSystemApplication.class, args);
    }

}
