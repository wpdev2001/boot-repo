package com.wp.couple;

public class Person {
    //Over here the person is tightly coupled with the animal class as it can only play with one animal
    Animal animal = new Animal();

    public void playWithAnimal(){
        animal.play();
    }
}
