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
    /*
    QUESTIONS:
    1. Why the different syntax for ResponseEntity.ok(newTodo) instead of using ResponseEntity.status(HttpStatus.OK).body(newTodo)?
    2. What is the reason behind using PUT instead we could use POST in updated the Todo?
     */

}
