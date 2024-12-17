package com.wp.mvc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class productController2 {
    //In order to fetch the value dynamically from the url we use PathVariable
    //URL: localhost:8080/getProduct2/1234/its just testing/8
    @GetMapping("/getProduct2/{productId}/{productName}/{rating}")
    public String getProduct2(
          @PathVariable("productId") int id,
          @PathVariable("productName") String name,
          @PathVariable int rating
    ){

        System.out.println("Product Name: " + name);
        System.out.println("Product Id: " + id);
        System.out.println("Product rating: " + rating);
        return "Testing of Path variable";

    }
}
