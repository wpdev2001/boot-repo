package com.wp.todo.service.impl;

import com.wp.todo.exceptions.ResourceNotFoundException;
import com.wp.todo.models.Todo;
import com.wp.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl1 implements TodoService {

    Logger logger = LoggerFactory.getLogger(TodoServiceImpl1.class);

    List<Todo> todoList = new ArrayList<>();

    public Todo createTodo(Todo todo) {
        todoList.add(todo);
        logger.info("TODO CREATED: {}", todo);
        return todo;
    }

    public List<Todo> getAllTodo() {
        logger.info("TODO LIST: {}", todoList);
        return todoList;
    }

    public Todo getTodoWithId(int todoId) {
        Todo todo = todoList.stream().filter(t -> t.getTodoId() == todoId).findAny().orElseThrow(() -> new ResourceNotFoundException("Todo with given id not found", HttpStatus.NOT_FOUND));
        logger.info("Todo With id: {} is {}", todoId,todo);
        return todo;
    }

//    METHOD 1
//    public Todo updateTodo(int todoId, Todo todoWithNewDetails) {
//        Todo todo = todoList.stream().filter(t -> t.getTodoId() == todoId).findAny().orElseThrow(() -> new ResourceNotFoundException("Todo with given id not found", HttpStatus.NOT_FOUND));
//        todo.setTodoId(todoId);
//        todo.setTitle(todoWithNewDetails.getTitle());
//        todo.setContent(todoWithNewDetails.getContent());
//        return todo;
//    }

    //    METHOD 2
    public Todo updateTodo(int todoId, Todo todoWithNewDetails) {
        return todoList.stream()
                .filter(t -> t.getTodoId() == todoId)
                .findAny()
                .map(t -> {
                    t.setTodoId(todoId);
                    t.setTitle(todoWithNewDetails.getTitle());
                    t.setContent(todoWithNewDetails.getContent());
                    t.setStatus(todoWithNewDetails.getStatus());
                    return t;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Todo with following Id not found",HttpStatus.NOT_FOUND));
    }

    public void deleteTodo(int todoId) {
        Optional<Todo> element = todoList.stream().
                filter(t -> t.getTodoId() == todoId)
                .findFirst();

        element.ifPresentOrElse(
                t -> todoList.remove(t),
                () -> {throw new ResourceNotFoundException("Todo with given Id not found", HttpStatus.NOT_FOUND);}
        );
    }

    @Override
    public void deleteMultipleTodo(List<Integer> ids) {
        //Need to code
    }
}
