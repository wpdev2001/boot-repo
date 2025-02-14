package com.wp.todo.service;

import com.wp.todo.models.Todo;

import java.util.List;

public interface TodoService {

    public Todo createTodo(Todo todo);
    public List<Todo> getAllTodo();
    public Todo getTodoWithId(int todoId);
    public Todo updateTodo(int todoId, Todo todoWithNewDetails);
    public void deleteTodo(int todoId);
    void deleteMultipleTodo(List<Integer> ids);
}