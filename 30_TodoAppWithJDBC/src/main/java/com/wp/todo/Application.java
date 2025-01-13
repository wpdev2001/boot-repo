package com.wp.todo;

import com.wp.todo.Model.Todo;
import com.wp.todo.dao.TodoDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

@SpringBootApplication
public class Application implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	TodoDao todoDao;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//This method runs by default when we start the application
//		System.out.println("Application Started:");
//		JdbcTemplate jdbcTemplate = todoDao.getJdbcTemplate();
//		logger.info("Template object : {}",jdbcTemplate);

		Todo todo = new Todo();
		todo.setId(123);
		todo.setTitle("This is testing spring jdbc");
		todo.setContent("It's working yeeey..!!");
		todo.setStatus("PENDING");
		todo.setAddedDate(new Date());
		todo.setToDoDate(new Date());

		todoDao.saveTodo(todo);

	}
}
