package com.wp.todo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "jpa_todo_table")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String title;
    private String content;
    private String status;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date addedDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date todoDate;
}
