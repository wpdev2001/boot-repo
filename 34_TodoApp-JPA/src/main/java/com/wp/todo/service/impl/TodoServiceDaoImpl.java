package com.wp.todo.service.impl;

import com.wp.todo.Model.Todo;
import com.wp.todo.dao.TodoDao;
import com.wp.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Primary
public class TodoServiceDaoImpl implements TodoService {

    @Autowired
    private TodoDao todoDao;

    @Override
    public Todo createTodo(Todo todo) {
        return todoDao.saveTodo(todo);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoDao.getAllTodos();
    }

    @Override
    public Todo getTodoWithId(int todoId) {
        return todoDao.getSingleTodo(todoId);
    }

    @Override
    public Todo updateTodo(int todoId, Todo todoWithNewDetails) {
        return todoDao.updateTodo(todoId,todoWithNewDetails);
    }

    @Override
    public void deleteTodo(int todoId) {
        todoDao.deleteTodo(todoId);
    }
}
