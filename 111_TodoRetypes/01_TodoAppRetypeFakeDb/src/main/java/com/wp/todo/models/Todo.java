package com.wp.todo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {

    private int todoId;
    private String title;
    private String content;
    private String status;
    @JsonFormat(pattern = "MM/dd/yy")
    private Date startDate;
    @JsonFormat(pattern = "MM/dd/yy")
    private Date todoDate;

}
