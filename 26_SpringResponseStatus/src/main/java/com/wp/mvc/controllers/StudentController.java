package com.wp.mvc.controllers;
import com.wp.mvc.model.Student;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @PostMapping("/create")
    public ResponseEntity<Map<String,Object>> createStudent(@RequestBody List<Student> students){
        //Printing the size of list
        System.out.println(students.size());
        System.out.println(students);
        Map<String,Object> data = new HashMap<>();
        data.put("Content",students);
        data.put("error","No error found");
        data.put("Date",new Date());

        //Method 1
        //ResponseEntity <Map<String,Object>> response = new ResponseEntity<>(data, HttpStatus.CREATED);

        //Method 2
        ResponseEntity <Map<String,Object>> response = ResponseEntity.status(HttpStatus.OK).body(data);

        return response;
    }
}
