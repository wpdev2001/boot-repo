package com.wp.TodoApp.repositories;

import com.wp.TodoApp.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
}
