package com.wp.couple;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Student {

    public Student(){
        System.out.println("creating student");
    }

    //PostConstruct annotation
    @PostConstruct
    public void postConstruct(){
        System.out.println("Method before instantiation of object");
    }

    //PreDestroy
    @PreDestroy
    public void preDestroy(){
        System.out.println("Run this method before destroying student bean/object");
    }


}
