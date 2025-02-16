package com.wp.todo.repositories;

import com.wp.todo.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<Todo,Integer> {
}
