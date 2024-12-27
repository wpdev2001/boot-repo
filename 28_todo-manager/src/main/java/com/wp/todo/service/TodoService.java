package com.wp.todo.service;

import com.wp.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoService {

    Logger logger = LoggerFactory.getLogger(TodoService.class);

    List<Todo> todoList = new ArrayList<>();

    public Todo createTodo(Todo todo){
        //create
        todoList.add(todo);
        logger.info("Todos list {}",this.todoList);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todoList;
    }

    public Todo getTodoWithId(int todoId) {
        //need to understand this line
        Todo todo = todoList.stream().filter(t -> todoId == t.getId()).findAny().get();
        logger.info("Todo : {}",todo);
        return todo;
    }

    public Todo updateTodo(int todoId, Todo todoWithNewDetails) {
        List<Todo> newUpdatedList = todoList.stream().map(t -> {
            if (t.getId() == todoId) {
                //Perform update
                t.setTitle(todoWithNewDetails.getTitle());
                t.setContent(todoWithNewDetails.getContent());
                t.setStatus(todoWithNewDetails.getStatus());
                return t;
            } else {
                return t;
            }
        }).collect(Collectors.toList());
        todoList = newUpdatedList;
        todoWithNewDetails.setId(todoId);
        return todoWithNewDetails;
    }

    public void deleteTodo(int todoId) {
        logger.info("DELETING TODO");
        List<Todo> newList = todoList.stream().filter(t-> t.getId() != todoId).collect(Collectors.toList());
        todoList = newList;
    }
}


