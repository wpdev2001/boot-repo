package com.wp.todo.services.Impl;

import com.wp.todo.entities.Todo;
import com.wp.todo.exceptions.ResourceNotFound;
import com.wp.todo.repositories.TodoJpaRepository;
import com.wp.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoJpaRepository jpaRepository;

    @Override
    public Todo createTodo(Todo todo) {
        return jpaRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return jpaRepository.findAll();
    }

    @Override
    public Todo getTodoWithId(int todoId) {
        return jpaRepository.findById(todoId).orElseThrow(() -> new ResourceNotFound("Todo with given Id not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Todo updateTodo(int todoId, Todo todoWithNewDetails) {

        Todo todo = jpaRepository.findById(todoId).orElseThrow(() -> new ResourceNotFound("Todo with given Id not found", HttpStatus.NOT_FOUND));
        todo.setTitle(todoWithNewDetails.getTitle());
        todo.setContent(todoWithNewDetails.getContent());
        todo.setStatus(todoWithNewDetails.getStatus());
        todo.setTodoDate(todoWithNewDetails.getTodoDate());

        return jpaRepository.save(todo);
    }

    @Override
    public void deleteTodo(int todoId) {
        Todo todo = jpaRepository.findById(todoId).orElseThrow(() -> new ResourceNotFound("Todo with given Id not found", HttpStatus.NOT_FOUND));
        jpaRepository.delete(todo);
    }


}
