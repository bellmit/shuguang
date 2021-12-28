package com.sofn.ducss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
@ComponentScan("com.sofn")
public class GzducssServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GzducssServiceApplication.class, args);
    }

}
