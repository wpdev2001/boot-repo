package com.wp.couple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Autowired
    Samosa samosa;

    public void personMethod(){
        samosa.eat();
    }

}
