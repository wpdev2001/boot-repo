package com.wp.TodoApp.services;

import com.wp.TodoApp.entities.Todo;

import java.util.List;

public interface TodoService {
    public Todo createTodo(Todo todo);
    public List<Todo> getAllTodo();
    public void deleteTodo(int id);
    public Todo getTodoById(int id);
}
