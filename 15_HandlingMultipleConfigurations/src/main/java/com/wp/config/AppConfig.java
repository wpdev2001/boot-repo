package com.wp.config;

import com.wp.beans.CartService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.wp"})
public class AppConfig {

    //bean for cartService
    @Bean("cartService1")
    public CartService cartService(){
        return new CartService();
    }
}
