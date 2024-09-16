package com.wp;

import com.wp.couple.Pepsi;
import com.wp.couple.Soda;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication

public class Application {

	public static void main(String[] args) {

		//Pepsi Class : Singleton
		//Soda Class : Prototype

		ApplicationContext context = SpringApplication.run(Application.class, args);

		Pepsi pepsi = context.getBean(Pepsi.class);
		System.out.println(pepsi);
		Soda soda = pepsi.getSoda();
		System.out.println(soda);

		Pepsi pepsi1 = context.getBean(Pepsi.class);
		System.out.println(pepsi1);
		Soda soda1 = pepsi1.getSoda();
		System.out.println(soda1);


	}


}
