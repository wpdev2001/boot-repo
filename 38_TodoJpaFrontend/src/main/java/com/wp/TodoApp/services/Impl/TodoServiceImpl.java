package com.wp.TodoApp.services.Impl;

import com.wp.TodoApp.entities.Todo;
import com.wp.TodoApp.repositories.TodoRepository;
import com.wp.TodoApp.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoJpaRepository;

    @Override
    public Todo createTodo(Todo todo) {
        return todoJpaRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoJpaRepository.findAll();
    }

    @Override
    public void deleteTodo(int id) {
        todoJpaRepository.deleteById(id);
    }

    @Override
    public Todo getTodoById(int id) {
        return todoJpaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found exception"));
    }
}
