package com.wp.todo.controllers;

import com.wp.todo.exceptions.TodoNotFoundException;
import com.wp.todo.models.Todo;
import com.wp.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoService;

    Random random = new Random();

    //create
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){

        //creating null pointer exception
        //String str = null;
        //logger.info("{}",str.length());

        //generating number format exception
        //Integer.parseInt("23lllfsfsd");

        //createTodo
        int id = random.nextInt(99999);
        todo.setId(id);
        //create date with system default date
        Date currentDate = new Date();
        logger.info("current date: {}",currentDate);
        logger.info("toDo date: {}",todo.getToDoDate());
        todo.setAddedDate(currentDate);
        logger.info("Create Todo");
        //call service to create Todo
        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    //getAll todo method
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        List<Todo> allTodos = todoService.getAllTodos();
        return new ResponseEntity<>(allTodos,HttpStatus.OK);
    }

    //getSingle todo by id
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodo(@PathVariable int todoId){
        Todo todo = todoService.getTodoWithId(todoId);
        return ResponseEntity.ok(todo);
    }

    //update todo
    //@PostMapping("/{todoId}") --> This works, then why didn't we used PostMapping
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todoWithNewDetails, @PathVariable int todoId){
        Todo newTodo = todoService.updateTodo(todoId, todoWithNewDetails);
        return ResponseEntity.ok(newTodo);
    }

    //delete
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){
        try{
            todoService.deleteTodo(todoId);
            return ResponseEntity.ok("Todo deleted Successfully!!!");
        }catch (TodoNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Defining Exception Handler
    /*
    In the above ways we could define as many as handlers for the different exceptions
    but this approach isn't an effective way to handle the exception as we have to
    write the exception handler code for each and every classes. So instead using this
    approach we will define one package and inside that we will define an Global exception
    handler.
     */

    //This handlers will gets invoked for this particular class whenever that particular exception occurs.
    //1. Null pointer Exception
    /*
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex){
        System.out.println(ex.getMessage());
        System.out.println("Null pointer exception genereated");
        return new ResponseEntity<>("Null pointer exception generated" + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //2. Number format Exception
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> numberFormatExceptionHandler(NullPointerException ex){
        System.out.println(ex.getMessage());
        System.out.println("Number format exception genereated");
        return new ResponseEntity<>("Number format exception generated" + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Also we can combine the above 2 annotations and pass the multiple exceptions
    //3. Handling multiple exception at once
    @ExceptionHandler(value = {NullPointerException.class, NumberFormatException.class})
    public ResponseEntity<String> multipleExceptionHandler(Exception ex){
        System.out.println(ex.getMessage());
        System.out.println("exception genereated");
        return new ResponseEntity<>("Exception generated" + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
     */


    /*
    QUESTIONS:
    1. Why the different syntax for ResponseEntity.ok(newTodo) instead of using ResponseEntity.status(HttpStatus.OK).body(newTodo)?
    2. What is the reason behind using PUT instead we could use POST in updated the Todo?
     */

}
