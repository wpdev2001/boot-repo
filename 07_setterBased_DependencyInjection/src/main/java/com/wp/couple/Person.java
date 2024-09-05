package com.wp.couple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Person {
    Animal animal;
    Student student;

    //Setter based injection


    //using autowired on setter: animal
    @Autowired
    public void setAnimal(Animal animal) {
        System.out.println("Setting animal");
        this.animal = animal;
    }

    //using autowired on setter: student
    @Autowired
    public void setStudent(Student student) {
        System.out.println("Setting Student");
        this.student = student;
    }

    public void playWithAnimal(){
        animal.play();
        student.detail();
    }
}
