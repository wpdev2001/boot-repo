package com.wp.SpringProfiles.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//Used to make Rest API to send the data in response
@RestController
public class HomeController {
    //Through this annoation we map the URL to some method

    //In order to read the value from the configuration file through its key we use this property
    @Value("${wp.profile.image.path}")
    private String profilePath;

    @RequestMapping("/todos")
    public List<String> justTest(){
        List<String> todos = Arrays.asList("Learn java", "Learn Spring Boot", "Develop project");
        return todos;
    }

    @RequestMapping("/profile-path")
    public String getProfilePath(){
        return this.profilePath;
    }
}
