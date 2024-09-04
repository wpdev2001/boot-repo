package com.wp.couple;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //Through this annotation this particular component is having more priority than that of Cat
public class Dog implements Animal{
    @Override
    public void play() {
        System.out.println("Dog is playing");
    }
}
