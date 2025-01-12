package com.wp.todo.controllers;

import com.wp.todo.Model.Todo;
import com.wp.todo.exceptions.TodoNotFoundException;
import com.wp.todo.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    TodoService todoService;

    Random random = new Random();

    Logger logger = LoggerFactory.getLogger(TodoController.class);

    //create Todo
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){
        logger.info("Creating Todo");
        int id = random.nextInt();
        todo.setId(id);
        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    //Get All Todos
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(){
        List<Todo> allTodos = todoService.getAllTodos();
        return ResponseEntity.ok(allTodos);
    }
    //get TodoWithId
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodoWithId(@PathVariable int todoId){
        Todo todo = todoService.getTodoWithId(todoId);
        return ResponseEntity.ok(todo);
    }

    //Update todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int todoId, @RequestBody Todo TodoWithNewDetails){
        Todo updatedTodo = todoService.updateTodo(todoId, TodoWithNewDetails);
        return ResponseEntity.ok(updatedTodo);
    }

    //Delete Todo
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId) {
        try{
            todoService.deleteTodo(todoId);
            return ResponseEntity.ok("Todo deleted Successfully!!!");
        }
        catch (TodoNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
