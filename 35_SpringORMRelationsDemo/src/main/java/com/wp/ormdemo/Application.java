package com.wp.ormdemo;


import com.wp.ormdemo.entities.Laptop;
import com.wp.ormdemo.entities.Student;
import com.wp.ormdemo.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
		Student student = new Student();
		student.setStudentId(101);
		student.setStudentName("AAA");
		student.setAbout("I am an java developer");

		Laptop laptop = new Laptop();
		laptop.setLaptopId(1);
		laptop.setBrand("Dell");
		laptop.setModelNumber("1234");

		//important bcs we are managing a column inside laptop table
		laptop.setStudent(student);

		//not necessary but good practice
		student.setLaptop(laptop);

		Student save = studentRepository.save(student);
		logger.info("Student name: {}",save.getStudentName());

	}
}
