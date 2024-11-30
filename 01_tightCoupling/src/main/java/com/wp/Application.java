package com.wp;

import com.wp.couple.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		Person person = new Person();
		person.playWithAnimal();

		SpringApplication.run(Application.class, args);
	}

}
