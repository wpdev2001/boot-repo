package com.wp.mvc.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("getProduct")
    public String getProduct(
            @RequestParam("productName") String name,
            @RequestParam(value="productId",defaultValue = "0", required = false) Integer id,

            /*URL cases:
            -------------
            localhost:8080/getProduct?productName=phone&rating=7
            @RequestParam(value="productId",required = false) Integer id, --> This line works
            @RequestParam(value="productId",required = false) int id, --> This throws error(see the error)
            */

            //over here the name of url parameter is same as the backend parameter
            //URLs:
            // localhost:8080/getProduct?productName=phone&productId=1312&rating=7
            //or we can also use && instead of using single '&'
            //Suppose there is a case where i missed to pass the parameter so in that case it will throw an error
            //In order to avoid that error we can pass a default value by using defaultValue parameter in the Request Param
            //Is sequence of passing the parameters matter?
            @RequestParam int rating

    ){
        System.out.println("Product Name: " + name);
        System.out.println("Product Id: " + id);
        System.out.println("Product rating: " + rating);
        return "Testing of Request param";
    }
}
