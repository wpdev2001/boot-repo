package com.wp.config;

import com.wp.web.HomeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    //Declare the HomeController bean
    @Bean
    public HomeController homeController(){
        return new HomeController();
    }

}
