package com.cmpe273.iot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @author arpit
 *
 */

@SpringBootApplication
@EnableScheduling
public class Cmpe273ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(Cmpe273ProjectApplication.class, args);
    }
}
