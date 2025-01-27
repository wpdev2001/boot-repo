package com.wp.todo.service.impl;

import com.wp.todo.Model.Todo;
import com.wp.todo.exceptions.ResourceNotFoundException;
import com.wp.todo.exceptions.TodoNotFoundException;

import com.wp.todo.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);

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
        Todo todo = todoList.stream().filter(t -> todoId == t.getId()).findAny().orElseThrow( () ->new ResourceNotFoundException("Todo not found with given id", HttpStatus.NOT_FOUND));
        //Run the below line and observe the output
        // Todo todo = todoList.stream().filter(t -> todoId == t.getId()).findAny().orElseThrow( () ->new ResourceNotFoundException("Todo not found with given id", HttpStatus.INTERNAL_SERVER_ERROR));
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

        Optional<Todo> todoToDel = todoList.stream()
                .filter(t->t.getId() == todoId)
                .findFirst();

        // If todoId found then delete that TODO
        if(todoToDel.isPresent()){
            todoList.remove(todoToDel.get());
            logger.info("Todo with ID {} deleted.",todoId);
        }
        else{
            logger.warn("Todo with ID {} not found.",todoId);
            throw new TodoNotFoundException("Todo with ID " + todoId + " not found.");
        }
    }
}


