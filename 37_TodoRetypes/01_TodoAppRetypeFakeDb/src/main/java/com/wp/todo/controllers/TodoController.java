package com.wp.todo.controllers;

import com.wp.todo.models.Todo;
import com.wp.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    Random random = new Random();

    //create Todo
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo){

        //Setting todo Id
        int id = random.nextInt(100);
        todo.setTodoId(id);

        //Setting todoDate
        Date currentDate = new Date();
        todo.setStartDate(currentDate);

        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

//    get all Todo
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo(){
        List<Todo> allTodo = todoService.getAllTodo();
        return new ResponseEntity<>(allTodo,HttpStatus.OK);
    }

//  get Todo with Id
    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodoWithId(@PathVariable int todoId){
        Todo todo = todoService.getTodoWithId(todoId);
        return ResponseEntity.ok(todo);
    }

//    update Todo
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int todoId,@RequestBody Todo todoWithNewDetails){
        Todo todo = todoService.updateTodo(todoId, todoWithNewDetails);
        return ResponseEntity.status(HttpStatus.OK).body(todo);
    }

//    delete Todo
    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodo(@PathVariable int todoId){
        todoService.deleteTodo(todoId);
        return ResponseEntity.status(HttpStatus.OK).body("Todo deleted successfully!!!");
    }

//    delete multiple todo
    @DeleteMapping
    public ResponseEntity<String> deleteMultipleTodos(@RequestParam List<Integer> ids){
        todoService.deleteMultipleTodo(ids);
        return ResponseEntity.status(HttpStatus.OK).body("Todos deleted successfully");
    }

}
