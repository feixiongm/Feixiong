package com.ascending.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"com.ascending"})
@ServletComponentScan(basePackages = {"com.ascending.filter"})
@EnableCaching
public class ApplicationBootstrap extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication.run(ApplicationBootstrap.class, args);


    }
}
