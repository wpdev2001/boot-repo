package com.wp.couple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {

//    @Autowired
//    @Qualifier("dog")
    Animal animal;

    @Autowired
    public Person(@Qualifier("cat") Animal animal) {
        this.animal = animal;
    }

    public void playWithAnimal(){
        animal.play();
    }
}
