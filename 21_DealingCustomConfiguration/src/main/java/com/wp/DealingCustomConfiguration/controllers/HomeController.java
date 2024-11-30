package com.wp.DealingCustomConfiguration.controllers;

import com.wp.DealingCustomConfiguration.config.WPConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

//Used to make Rest API to send the data in response
@RestController
public class HomeController {

    @Autowired
    private WPConfig wpConfig;

    @RequestMapping("/wpconfig")
    public WPConfig getWpConfig(){
        System.out.println(this.wpConfig);
        return this.wpConfig;
    }


}
