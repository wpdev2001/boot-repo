package com.wp.__looseCoupling;

import com.wp.__looseCoupling.couple.Animal;
import com.wp.__looseCoupling.couple.Cat;
import com.wp.__looseCoupling.couple.Dog;
import com.wp.__looseCoupling.couple.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
//		Animal animal = new Dog();
		Animal animal = new Cat();
		Person p = new Person(animal);
		p.playWithAnimal();
		SpringApplication.run(Application.class, args);
	}

}
