package com.wp.todo.service;

import com.wp.todo.Model.Todo;

import java.util.List;

public interface TodoService {

    public Todo createTodo(Todo todo);
    public List<Todo> getAllTodos();
    public Todo getTodoWithId(int todoId);
    public Todo updateTodo(int todoId, Todo todoWithNewDetails);
    public void deleteTodo(int todoId);

}
