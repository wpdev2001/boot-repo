package com.wp.TodoApp.controllers;

import com.wp.TodoApp.entities.Todo;
import com.wp.TodoApp.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("todo",new Todo());
        return "todo-form";
    }

    @PostMapping("/add")
    public String createTodoHandler(@ModelAttribute Todo todo){
        //Setting addedDate
        todo.setAddedDate(new Date());
        todoService.createTodo(todo);
        return "redirect:/todos";
    }

    //getAll Todo
    @GetMapping
    public String getAllTodoHandler(Model model){
        List<Todo> allTodo = todoService.getAllTodo();
        model.addAttribute("todos",allTodo);
        return "todo-list";
    }

    //get Single Todo
    @GetMapping("/{id}")
    public String getTodoByIdHandler(@PathVariable int id, Model model){
        Todo todoById = todoService.getTodoById(id);
        model.addAttribute("todo",todoById);
        return "todo-detail";
    }

    //update Todo
    @PostMapping("/update/{id}")
    public String updateTodoHandler(@PathVariable int id, @ModelAttribute Todo todo){
        Todo existingTodo = todoService.getTodoById(id);

        //updating fields
        existingTodo.setTitle(todo.getTitle());
        existingTodo.setContent(todo.getContent());
        existingTodo.setTodoDate(todo.getTodoDate());

        todoService.createTodo(existingTodo);
        return "redirect:/todos";
    }

    //show update form
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable int id, Model model){
        Todo todoById = todoService.getTodoById(id);
        model.addAttribute("todo",todoById);
        return "edit-todo";
    }

    //delete Todo
    @PostMapping("/delete")
    public String deleteTodoHandler(@RequestParam("id") int id){
        todoService.deleteTodo(id);
        return "redirect:/todos";
    }

}
