package com.wp.couple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {

    //property based dependency injection

    @Autowired
    Animal animal;
    @Autowired
    Student student;

    public void playWithAnimal(){
        animal.play();
        student.detail();
    }
}
