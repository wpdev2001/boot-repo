package com.wp.ormdemo.services.impl;

import com.wp.ormdemo.entities.User;
import com.wp.ormdemo.repositories.UserRepository;
import com.wp.ormdemo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        logger.info("User saved: {}",savedUser.getId());
        return savedUser;
    }

    @Override
    public User updateUser(User newUser, int userId) {
//        1: get the old  user from database and update it through new values
        User oldUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with given id not found"));
        oldUser.setName(newUser.getName());
        oldUser.setCity(newUser.getCity());
        oldUser.setAge(newUser.getAge());

//        2: perform the update user
        User updatedUser = userRepository.save(oldUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with given id"));
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUser = userRepository.findAll();
        return allUser;
    }

    @Override
    public User getUserById(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(() -> new RuntimeException("User with given id not found"));
        return user;
    }
}
