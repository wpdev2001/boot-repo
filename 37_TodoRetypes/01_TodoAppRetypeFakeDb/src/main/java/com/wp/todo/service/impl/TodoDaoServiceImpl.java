package com.wp.todo.service.impl;

import com.wp.todo.dao.TodoDao;
import com.wp.todo.exceptions.ResourceNotFoundException;
import com.wp.todo.models.Todo;
import com.wp.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class TodoDaoServiceImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    public Todo createTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public List<Todo> getAllTodo() {
        return todoDao.getAllTodos();
    }

    @Override
    public Todo getTodoWithId(int todoId) {
        try{
            return todoDao.getTodoWithId(todoId);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Todo with given Id not found", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Todo updateTodo(int todoId, Todo todoWithNewDetails) {
        return todoDao.updateTodo(todoId, todoWithNewDetails);
    }

    @Override
    public void deleteTodo(int todoId) {
        todoDao.deleteTodo(todoId);
    }

    @Override
    public void deleteMultipleTodo(List<Integer> ids) {
        todoDao.deleteMultipleTodo(ids);
    }

}
