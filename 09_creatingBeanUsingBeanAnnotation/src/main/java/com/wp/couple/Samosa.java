package com.wp.couple;

public class Samosa {
    //In order to declare the samosa as a bean using bean annotation we have to use @Bean annotation on the method level bcz the bean annotation works on method

    String name;

    public Samosa(String name){
        System.out.println("This is " + name);
        this.name=name;
    }
    public void eat(){
        System.out.println("wow samosa is very spicy!!!");
        System.out.println(name);
    }
}
