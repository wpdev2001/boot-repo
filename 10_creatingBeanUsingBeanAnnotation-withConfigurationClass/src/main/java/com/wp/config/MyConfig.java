package com.wp.config;

import com.wp.couple.Samosa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MyConfig {

    //Declaring the bean using @Bean

    @Bean(name="samosa1")
//	@Primary
    public Samosa getSamosa1(){
        return new Samosa("Tandori");
    }

    @Bean(name="samosa2")
    @Primary
    public Samosa getSamosa2(){
        return new Samosa("Meeta");
    }

}
