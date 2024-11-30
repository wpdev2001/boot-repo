package com.wp.__looseCoupling.couple;

public class Person {

    Animal animal;

    public Person(Animal animal) {
        this.animal = animal;
    }

    public void playWithAnimal(){
        animal.play();
    }

}
