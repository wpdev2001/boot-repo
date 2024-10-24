package com.wp.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Component
//@RequestMapping --> Instead of using these 2 annotations we can use only single annotation
//NOTE : If we define the request mapping on method then we have to also define it on class
@Controller
public class AboutController {
    @RequestMapping("/about")
    public String aboutSection(){
        System.out.println("This is about section");
        //From this return statement it will gonna search for about.html inside templates folder
        return "about";
    }
}
