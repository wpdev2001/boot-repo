package com.wp.mvc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @PostMapping("/getStudent")
    public ResponseEntity<String> createStudent(){
        String data = "product student";
        //You can give any status as an response after successful running of ths API
        ResponseEntity<String> response = new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        return response;
    }
}
