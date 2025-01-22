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
import java.util.List;

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
//==========================================================================
		//Creating TODO and saving into db
//		Todo todo = new Todo();
//		todo.setId(456);
//		todo.setTitle("This is testing JAVA jdbc");
//		todo.setContent("It's working ..!!");
//		todo.setStatus("COMPLETED");
//		todo.setAddedDate(new Date());
//		todo.setToDoDate(new Date());
//
//		todoDao.saveTodo(todo);
//==========================================================================
//		GET Single TODO
//		Todo singleTodo = todoDao.getSingleTodo(123);
//		logger.info("SINGLE TODO: {}", singleTodo);
//==========================================================================
		//GET ALL TODOS
//		List<Todo> allTodos = todoDao.getAllTodos();
//		logger.info("ALL TODOS: {}", allTodos);

//==========================================================================

//		//UPDATE TODO
//		//Getting the older values in todo by using already created API
//		Todo singleTodo = todoDao.getSingleTodo(456);
//		logger.info("TODO BEFORE UPDATE: {}", singleTodo);
//
//		//Setting new values to the old Todo object & updating it
//		singleTodo.setTitle("Learn Spring boot course");
//		singleTodo.setContent("I have completed the update operation");
//		singleTodo.setStatus("SUCCESSFULL");
//		singleTodo.setAddedDate(new Date());
//		singleTodo.setToDoDate(new Date());
//		todoDao.updateTodo(456,singleTodo);
//		logger.info("TODO AFTER UPDATE: {}", singleTodo);

//==========================================================================

//		DELETE SINGLE TODO
//		todoDao.deleteTodo(456);

//==========================================================================
//		DELETE MULTIPLE TODOS
		todoDao.deleteMultipleTodo(new int[]{456,789});

	}
}
