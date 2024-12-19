package com.wp.todo.service;

import com.wp.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TodoService {

    Logger logger = LoggerFactory.getLogger(TodoService.class);

    List<Todo> list = new ArrayList<>();

    public Todo createTodo(Todo todo){
        //create
        list.add(todo);
        logger.info("Todos list {}",this.list);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return list;
    }

    public Todo getTodoWithId(int todoId) {
        //need to understand this line
        Todo todo = list.stream().filter(t -> todoId == t.getId()).findAny().get();
        logger.info("Todo : {}",todo);
        return todo;
    }
}
