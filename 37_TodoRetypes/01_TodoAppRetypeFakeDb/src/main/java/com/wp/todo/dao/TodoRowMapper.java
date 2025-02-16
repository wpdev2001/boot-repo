package com.wp.todo.dao;

import com.wp.todo.models.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();
        todo.setTodoId(rs.getInt("todoId"));
        todo.setTitle(rs.getString("title"));
        todo.setContent(rs.getString("content"));
        todo.setStatus(rs.getString("status"));
        todo.setStartDate(rs.getDate("addedDate"));
        todo.setTodoDate(rs.getDate("todoDate"));
        return todo;
    }
}
