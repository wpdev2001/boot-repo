package com.wp.couple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class Pepsi {

    @Autowired
    private Soda soda;

    public Pepsi(){
        System.out.println("Creating PEPSI");

    }
    public void drink(){
        System.out.println("Pepsi is very cool");

    }

    public Soda getSoda() {
        return soda;
    }

    public void setSoda(Soda soda) {
        this.soda = soda;
    }
}
