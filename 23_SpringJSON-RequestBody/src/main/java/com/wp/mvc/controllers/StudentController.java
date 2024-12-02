package com.wp.mvc.controllers;
import com.wp.mvc.model.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

//    METHOD 1  Getting the data from JSON using post request
//    @PostMapping("/create")
//    public void createStudent(Map<String, Object> data){
//        System.out.println(data);
//        Object name = data.get("name");
//        Object empId = data.get("empId");
//    }

    @PostMapping("/create")
    public String createStudent(@RequestBody List<Student> students){
        //Printing the size of list
        System.out.println(students.size());
        System.out.println(students);
        return "created";
    }
}
