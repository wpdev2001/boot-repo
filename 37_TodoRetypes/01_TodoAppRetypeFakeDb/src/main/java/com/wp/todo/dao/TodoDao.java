package com.wp.todo.dao;
import com.wp.todo.exceptions.ResourceNotFoundException;
import com.wp.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoDao {

    private JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(TodoDao.class);

    public TodoDao(@Autowired JdbcTemplate jdbcTemplate) {
        //create table if not exists
        this.jdbcTemplate = jdbcTemplate;
        String createTable = "CREATE TABLE IF NOT EXISTS jdbc_todo ("
                + "todoId INT PRIMARY KEY, "
                + "title VARCHAR(20), "
                + "content VARCHAR(50), "
                + "status VARCHAR(20), "
                + "addedDate DATETIME, "
                + "todoDate DATETIME)";
        int update = jdbcTemplate.update(createTable);
        logger.info("CREATED TABLE");
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Todo saveTodo(Todo todo) {
        String sql = "INSERT INTO jdbc_todo(todoId,title,content,status,addedDate,todoDate) VALUES(?,?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, todo.getTodoId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getStartDate(), todo.getTodoDate());
        logger.info("TODO ADDED: {}", update);
        return todo;
    }

    public List<Todo> getAllTodos() {
        String query = "SELECT * FROM jdbc_todo";
        List<Todo> todoList = jdbcTemplate.query(query, new TodoRowMapper());
        return todoList;
    }

    public Todo getTodoWithId(int todoId) {
        String query = "SELECT * FROM jdbc_todo WHERE todoId=?";
        try {
            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                Todo todo = new Todo();
                todo.setTodoId(rs.getInt("todoId"));
                todo.setTitle(rs.getString("title"));
                todo.setContent(rs.getString("content"));
                todo.setStatus(rs.getString("status"));
                todo.setStartDate(rs.getDate("addedDate"));
                todo.setTodoDate(rs.getDate("todoDate"));
                return todo;
            }, todoId);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException("Todo not found", 1);
        }
    }

    public Todo updateTodo(int todoId, Todo todoWithNewDetails) {
        String query = "UPDATE jdbc_todo SET title=?,content=?,status=?,addedDate=?,todoDate=? WHERE todoId=?";

        int update = jdbcTemplate.update(
                query,
                todoWithNewDetails.getTitle(),
                todoWithNewDetails.getContent(),
                todoWithNewDetails.getStatus(),
                todoWithNewDetails.getStartDate(),
                todoWithNewDetails.getTodoDate(),
                todoId
        );

        if (update == 0) {
            throw new ResourceNotFoundException("Todo with given Id not found", HttpStatus.NOT_FOUND);
        }
        todoWithNewDetails.setTodoId(todoId);
        return todoWithNewDetails;

    }

    public void deleteTodo(int todoId) {
        String query = "DELETE FROM jdbc_todo WHERE todoId=?";
        int update = jdbcTemplate.update(query, todoId);

        if (update == 0) {
            throw new ResourceNotFoundException("Todo with given Id not found", HttpStatus.NOT_FOUND);
        }

    }

    public void deleteMultipleTodo(List<Integer> ids) {

        String query = "DELETE FROM jdbc_todo WHERE todoId=?";

        int[] todoIds = jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids.get(i);
                ps.setInt(1, id);
            }

            @Override
            public int getBatchSize() {
                return ids.size();
            }
        });

        for (Integer i : todoIds) {
            logger.info("DELETED {}", i);
        }
    }
}