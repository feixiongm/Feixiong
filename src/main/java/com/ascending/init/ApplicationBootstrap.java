package com.ascending.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.ascending"})
public class ApplicationBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootstrap.class, args);
    }
}
