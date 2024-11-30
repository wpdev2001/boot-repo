package com.wp.couple;

public class Person {

    //Here the person is tightly coupled Animal bcz he can't play with other animal and he is only having one option
    Animal animal = new Animal();

    public void playWithAnimal(){
        animal.play();
    }
}
