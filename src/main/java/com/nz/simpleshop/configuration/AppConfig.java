package com.nz.simpleshop.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.nz.simpleshop")
public class AppConfig {
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}