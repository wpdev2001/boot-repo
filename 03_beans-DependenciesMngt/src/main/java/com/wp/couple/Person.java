package com.wp.couple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

    @Autowired
    Animal animal;

    public Person(Animal animal) {
        this.animal = animal;
    }

    public void playWithAnimal(){
        animal.play();
    }
}
