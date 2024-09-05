package com.wp.couple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {
    Animal animal;
    Student student;

    //Constructor based injection
    //In this code i have injected 2 dependencies within person i.e,. one is animal and the second one is student
    @Autowired
    public Person(Animal animal, Student student){
        System.out.println("calling constructor autowiring!!!");
        this.animal = animal;
        this.student = student;
    }

    public void playWithAnimal(){
        animal.play();
        student.detail();
    }
}
