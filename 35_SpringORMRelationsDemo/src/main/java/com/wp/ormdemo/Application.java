package com.wp.ormdemo;


import com.wp.ormdemo.entities.Address;
import com.wp.ormdemo.entities.Laptop;
import com.wp.ormdemo.entities.Student;
import com.wp.ormdemo.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//ONE TO ONE
//		Student student = new Student();
//		student.setStudentId(101);
//		student.setStudentName("AAA");
//		student.setAbout("I am an java developer");
//
//		Laptop laptop = new Laptop();
//		laptop.setLaptopId(1);
//		laptop.setBrand("Dell");
//		laptop.setModelNumber("1234");
//
//		//important bcs we are managing a column inside laptop table
//		laptop.setStudent(student);
//
//		//not necessary but good practice
//		student.setLaptop(laptop);
//
//		Student save = studentRepository.save(student);
//		logger.info("Student name: {}",save.getStudentName());

        //ONE TO MANY
		Student student = new Student();
		student.setStudentId(105);
		student.setStudentName("EEE");
		student.setAbout("I am an C# developer");

        Address address1 = new Address();
        address1.setAddress_id(111);
        address1.setCity("Pune");
        address1.setCountry("India");
        address1.setStreet("new street 102");
        address1.setStudent(student); //address table having student info

        Address address2 = new Address();
        address2.setAddress_id(222);
        address2.setCity("Banglore");
        address2.setCountry("India");
        address2.setStreet("new street 103");
        address2.setStudent(student); //address table having student info

        List<Address> addressList = new ArrayList<>();
        addressList.add(address1);
        addressList.add(address2);

        //student have both addresses
        student.setAddressList(addressList);

        Student save = studentRepository.save(student);
        logger.info("Created Student with address");


    }
}
