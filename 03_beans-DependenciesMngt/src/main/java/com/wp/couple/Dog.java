package com.wp.couple;

import org.springframework.stereotype.Component;

//@Component --> Commenting it here bcz its being already used in Cat class
public class Dog implements Animal{
    @Override
    public void play() {
        System.out.println("Dog is playing");
    }
}
