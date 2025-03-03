package com.wp.estore.services;

import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.dtos.UserDto;
import com.wp.estore.entities.User;

import java.util.List;

public interface UserService {
    //create
    UserDto createUser(UserDto userDto);

    //update
    UserDto updateUser(UserDto userDto, String userId);

    //get all users
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get user by id
    UserDto getUserById(String userId);

    //get user by email
    UserDto getUserByEmail(String email);

    //Search User
    List<UserDto> searchUser(String keyword);

    //delete user
    void deleteUser(String userId);


}
