package com.wp.todo.dao;

import com.wp.todo.Model.Todo;
import com.wp.todo.helper.Helper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();
        todo.setId(rs.getInt("id"));
        todo.setTitle(rs.getString("title"));
        todo.setContent(rs.getString("content"));
        todo.setStatus(rs.getString("status"));

        //For time we need to handle it in different way so creating helper class
        //Formating date: In short we are fomating the dates
        todo.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
        todo.setToDoDate(Helper.parseDate((LocalDateTime) rs.getObject("todoDate")));

        return todo;
    }
}
