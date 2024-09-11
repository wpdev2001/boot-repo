package com.wp;

import com.wp.couple.Pepsi;
import com.wp.couple.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Application.class, args);
		Person personBean = context.getBean(Person.class);
//		System.out.println(personBean);
		personBean.personMethod();

		//First request for pepsi bean
		Pepsi bean1 = context.getBean(Pepsi.class);
		System.out.println(bean1);

		//Second request for pepsi bean
		Pepsi bean2 = context.getBean(Pepsi.class);
		System.out.println(bean2);

	}


}
