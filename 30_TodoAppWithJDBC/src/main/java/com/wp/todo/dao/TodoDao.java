package com.wp.todo.dao;

import com.wp.todo.Model.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class TodoDao {

    Logger logger = LoggerFactory.getLogger(TodoDao.class);


    private JdbcTemplate jdbcTemplate;

    public TodoDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

        //create table if doesn't exists
        String createTable = "create table IF NOT EXISTS Todos(id int primary key, title varchar(100) NOT NULL,content varchar(50),status varchar(20),addedDate datetime,todoDate datetime)";
        int update = jdbcTemplate.update(createTable);
        logger.info("TODO TABLE CREATED {}",update);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //save todo in db
    public Todo saveTodo(Todo todo){

        String insertQuery = "insert into Todos(id,title,content,status,addedDate,toDoDate) values(?,?,?,?,?,?)";

        int rows = jdbcTemplate.update(insertQuery,todo.getId(),todo.getTitle(),todo.getContent(),todo.getStatus(),todo.getAddedDate(),todo.getToDoDate());

        logger.info("JDBC OPERATION: {} inserted",rows);

        return todo;
    }

    //get single todo from db
    //get all todo from db
    //update todo
    //delete todo

}
