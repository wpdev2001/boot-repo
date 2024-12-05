package com.wp.mvc.controllers;
import com.wp.mvc.model.Student;
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
    public Map<String, Object> createStudent(@RequestBody List<Student> students){
        //Printing the size of list
        System.out.println(students.size());
        System.out.println(students);
        Map<String,Object> data = new HashMap<>();
        data.put("Content",students);
        data.put("error","No error found");
        data.put("Date",new Date());
        return data;
    }
}
