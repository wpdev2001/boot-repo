package com.wp.todo.service.impl;

import com.wp.todo.Model.Todo;
import com.wp.todo.dao.TodoJpaRepository;
import com.wp.todo.exceptions.ResourceNotFoundException;
import com.wp.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class TodoJpaServiceImpl implements TodoService {

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @Override
    public Todo createTodo(Todo todo) {
        return todoJpaRepository.save(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoJpaRepository.findAll();
    }

    @Override
    public Todo getTodoWithId(int todoId) {
        return todoJpaRepository.findById(todoId).orElseThrow(()->new ResourceNotFoundException("Todo not found with given Id", HttpStatus.NOT_FOUND));
    }

    @Override
    public Todo updateTodo(int todoId, Todo todoWithNewDetails) {
        Todo todo1 = todoJpaRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo not found with given Id", HttpStatus.NOT_FOUND));
        todo1.setTitle(todoWithNewDetails.getTitle());
        todo1.setContent(todoWithNewDetails.getContent());
        todo1.setStatus(todoWithNewDetails.getStatus());
        todo1.setToDoDate(todoWithNewDetails.getToDoDate());
        return todoJpaRepository.save(todo1);
    }

    @Override
    public void deleteTodo(int todoId) {
        Todo todo1 = todoJpaRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException("Todo not found with given Id", HttpStatus.NOT_FOUND));
        todoJpaRepository.delete(todo1);
    }
}
