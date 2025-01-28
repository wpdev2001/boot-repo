package com.wp.ormdemo.services;

import com.wp.ormdemo.entities.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    User updateUser(User user,int userId);
    void deleteUser(int userId);
    List<User> getAllUser();
    User getUserById(int userId);

}
