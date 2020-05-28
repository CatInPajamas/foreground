package com.crowd.foreground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ImportResource(locations = {"classpath:applicationContext.xml"})
@EnableAutoConfiguration
@EnableWebMvc
public class ForegroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(ForegroundApplication.class, args);
    }

}
