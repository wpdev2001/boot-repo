package com.wp.mvc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//@Controller --> not needed
@RestController  // --> This annotation consists both the Controller and ResponseBody annotation
public class ApiController {
    @RequestMapping("/hello")
    //@ResponseBody --> Not needed
    public String helloApp(){
        return "Hello, how are you??";
    }

    @RequestMapping("/users")
    public List<String> userData(){
        return Arrays.asList("Ram","Shyam","Chhiku");
    }
}
