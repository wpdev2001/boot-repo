package com.wp;

import com.wp.couple.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {

		//Pepsi Class : Singleton
		//Soda Class : Prototype

		ApplicationContext context = SpringApplication.run(Application.class, args);

//		Student student = context.getBean(Student.class);
//		System.out.println(student);


	}


}
