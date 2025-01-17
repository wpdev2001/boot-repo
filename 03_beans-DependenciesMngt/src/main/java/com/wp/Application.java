package com.wp;

import com.wp.couple.Animal;
import com.wp.couple.Dog;
import com.wp.couple.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		//about the beans using @Component Annotation

		//about the dependencies using @Autowired Annotation

		//Where to scan for beans
		//by default we have our beans in com.wp.couple


		/*
		Animal animal = new Dog();
		Person person = new Person(animal);
		person.playWithAnimal();
		*/


		ApplicationContext context = SpringApplication.run(Application.class, args);

		Person personBean = context.getBean(Person.class);
		personBean.playWithAnimal();
	}

}
