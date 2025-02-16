package com.wp.todo.controllers;

import com.wp.todo.entities.Todo;
import com.wp.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    //create Todo
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){

        todo.setAddedDate(new Date());
        Todo todo1 = todoService.createTodo(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo1);
    }

    //getAllTodo
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        return new ResponseEntity<>(todoService.getAllTodos(),HttpStatus.OK);
    }

    //get Todo With Id
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId){
        return ResponseEntity.status(HttpStatus.OK).body(todoService.getTodoWithId(todoId));
    }

    //update Todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@PathVariable int todoId,@RequestBody Todo todoWithNewDetails){
        return ResponseEntity.ok(todoService.updateTodo(todoId,todoWithNewDetails));
    }
    //delete todo
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo deleted Successfully!!!");
    }

}
