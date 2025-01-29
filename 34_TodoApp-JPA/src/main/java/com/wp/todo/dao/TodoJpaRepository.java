package com.wp.todo.dao;

import com.wp.todo.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaRepository extends JpaRepository<Todo,Integer> {
}
