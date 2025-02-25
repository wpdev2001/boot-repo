package com.wp.TodoApp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "todo-jpa-front")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    private String status;
    //Used when the date is submitted from an HTML form.
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addedDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date todoDate;
}
