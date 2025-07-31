package com.wp.estore.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    private String userId;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_email",length = 30)
    private String email;
    @Column
    private String password;
    private String gender;
    @Column(length = 1000)
    private String about;
    @Column(name = "user_image_name")
    private String imageName;
    @OneToMany(mappedBy ="user" ,cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();
}
