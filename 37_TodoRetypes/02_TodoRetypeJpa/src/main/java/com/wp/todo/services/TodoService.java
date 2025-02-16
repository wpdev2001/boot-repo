package com.wp.todo.services;

import com.wp.todo.entities.Todo;

import java.util.List;

public interface TodoService {
    Todo createTodo(Todo todo);
    List<Todo> getAllTodos();
    Todo getTodoWithId(int todoId);
    Todo updateTodo(int todoId, Todo todoWithNewDetails);
    void deleteTodo(int todoId);
}
