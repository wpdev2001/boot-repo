package com.wp.couple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Autowired
    Pepsi pepsi;

    public void personMethod(){
        pepsi.drink();
    }

}
