package com.wp.mvc;

import com.wp.mvc.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		Student s = new Student();

		s.setName("AAA");
		s.setRno(202);
		s.setPhone("00000000");
		s.setDept("CS");

		System.out.println(s);
	}

}
