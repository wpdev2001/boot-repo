package com.wp.mvc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {
    //URL: localhost:8080/getProduct3/1234/its just testing/8

    //Creating Logger Object
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/getProduct3/{productId}/{productName}/{rating}")
    public String getProduct3(
            @PathVariable("productId") int productId,
            @PathVariable("productName") String productName,
            @PathVariable int rating
    ){

        //Based on multiple values we can simply place the placeholder '{}'.
        //The main advantage of using logger is that we can make code to store the logs in a separate file
        //To do the above thing add a line within application.properties file
        logger.error("productName= {} {}",productName,"testing argument");
        logger.warn("productId= {}",productId);
        logger.info("productRating= {}",rating);
        //by default we have logging enabled for error, warning and info and if you want to use debug then we have to enable it by adding a line in application.properties file
        logger.debug("This is a test debug message");

        return "Testing of Path variable";

    }
}
