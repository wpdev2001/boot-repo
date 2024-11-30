package com.wp.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String loginRequestHandler(){
        System.out.println("Login");
        return "login";
    }
}
