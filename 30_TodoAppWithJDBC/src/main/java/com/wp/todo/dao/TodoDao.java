package com.wp.todo.dao;

import com.wp.todo.Model.Todo;
import com.wp.todo.helper.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class TodoDao {

    Logger logger = LoggerFactory.getLogger(TodoDao.class);


    private JdbcTemplate jdbcTemplate;

    //injecting jdbcTemplate dependency using constructor.
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
    public Todo getSingleTodo(int id){

        //Getting the data from the db
        String query = "select * from Todos WHERE id=?";
        Map<String, Object> todoData = jdbcTemplate.queryForMap(query, id);
        logger.info("TODO: {}",todoData);

        //Setting the data to Todo obj in order to return
        Todo todo = new Todo();
        todo.setId((int)todoData.get("id"));
        todo.setTitle((String)todoData.get("title"));
        todo.setContent((String)todoData.get("content"));
        todo.setStatus((String) todoData.get("status"));

        //For time we need to handle it in different way so creating helper class
        //Formating date: In short we are fomating the dates
        todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
        todo.setToDoDate(Helper.parseDate((LocalDateTime) todoData.get("todoDate")));

        return todo;
    }

    //get all todo from db DECODE IT MORE!!!
    public List<Todo> getAllTodos(){
        String query = "Select * from Todos";
        List<Map<String, Object>> todoInListOfMap = jdbcTemplate.queryForList(query);

        List<Todo> todoList = todoInListOfMap.stream().map((myMap) -> {

            Todo todo = new Todo();
            todo.setId((int) myMap.get("id"));
            todo.setTitle((String) myMap.get("title"));
            todo.setContent((String) myMap.get("content"));
            todo.setStatus((String) myMap.get("status"));

            //For time we need to handle it in different way so creating helper class
            //Formating date: In short we are fomating the dates
            todo.setAddedDate(Helper.parseDate((LocalDateTime) myMap.get("addedDate")));
            todo.setToDoDate(Helper.parseDate((LocalDateTime) myMap.get("todoDate")));

            return todo;

        }).collect(Collectors.toList());

        return todoList;
    }

    //update todo
    public Todo updateTodo(int oldId, Todo newTodo){

        String query = "update Todos set title=?,content=?,status=?,addedDate=?,todoDate=? WHERE id=?";
        int update = jdbcTemplate.update(query, newTodo.getTitle(), newTodo.getContent(), newTodo.getStatus(), newTodo.getAddedDate(), newTodo.getToDoDate(), oldId);
        logger.info("UPDATED: {}",update );
        newTodo.setId(oldId);

        return newTodo;

    }

    //delete SINGLE todo
    public void deleteTodo(int id){

        String query = "delete from Todos WHERE id=?";
        int delete = jdbcTemplate.update(query,id);
        logger.info("DELETED: {}", delete);

    }
    
    //Deleting Multiple Todos --> DO more research about Batch processes
    public void deleteMultipleTodo(int ids[]){
        String query = "delete from Todos WHERE id=?";

        int[] ints = jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                int id = ids[i];
                ps.setInt(1, id);
            }

            @Override
            public int getBatchSize() {
                //Give the size here
                return ids.length;
            }
        });

        for(int i : ints){
            logger.info("DELETED {}", i);
        }

    }
    

}
