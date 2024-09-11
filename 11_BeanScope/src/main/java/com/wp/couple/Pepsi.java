package com.wp.couple;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
// Method 1
//@Scope("singleton")
//@Scope("prototype")

// Method 2
//@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Pepsi {

    public Pepsi(){
        System.out.println("Creating PEPSI");

    }
    public void drink(){
        System.out.println("Pepsi is very cool");

    }
}
