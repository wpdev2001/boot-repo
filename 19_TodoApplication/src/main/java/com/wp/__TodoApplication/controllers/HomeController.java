package com.wp.__TodoApplication.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//Used to make Rest API to send the data in response
@RestController
public class HomeController {
    //Through this annoation we map the URL to some method
    @RequestMapping("/todos")
    public List<String> justTest(){
        List<String> todos = Arrays.asList("Learn java", "Learn Spring Boot", "Develop project");
        return todos;
    }
}
