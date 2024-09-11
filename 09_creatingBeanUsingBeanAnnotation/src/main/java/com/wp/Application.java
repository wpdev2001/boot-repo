package com.wp;

import com.wp.couple.Person;
import com.wp.couple.Samosa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
//In order to declare the samosa as a bean using bean annotation we have to use @Bean annotation on the method level bcz the bean annotation works on method
public class Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class, args);
		Person personBean = context.getBean(Person.class);
		personBean.personMethod();

	}
	//Declaring the bean using @Bean

	@Bean(name="samosa1")
//	@Primary
	public Samosa getSamosa1(){
		return new Samosa("Tandori");
	}

	@Bean(name="samosa2")
	@Primary
	public Samosa getSamosa2(){
		return new Samosa("Meeta");
	}

}
