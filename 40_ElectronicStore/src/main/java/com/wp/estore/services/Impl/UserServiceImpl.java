package com.wp.estore.services.Impl;

import com.wp.estore.dtos.UserDto;
import com.wp.estore.entities.User;
import com.wp.estore.repositories.UserRepository;
import com.wp.estore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        //generate unique id in string format
        String userId = UUID.randomUUID().toString();
        userDto.setUserId(userId);

        //Converting dto -> entity
        User user = dtoToEntity(userDto);
        User savedUser = userRepository.save(user); // Conversion is required bcz we can't pass DTO object directly to save() method.

        //Converting entity -> dto
        UserDto newDto = entityToDto(savedUser); // this conversion is required bcz our return type is DTO
        return newDto;

    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Todo with given Id not found"));
        user.setName(userDto.getName());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setPassword(userDto.getPassword());
        user.setImageName(userDto.getImageName());

        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);

        return updatedDto;

    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Todo with given Id not found"));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> user_list = userRepository.findAll();

        //METHOD 1(NORMAL APPROACH): Converting User list to UserDtoList
//        List<UserDto> userDtosList = new ArrayList<>();
//
//        for(User user : user_list){
//            UserDto userDto = entityToDto(user);
//            userDtosList.add(userDto);
//        }
//        return userDtosList;

        //MEHTHOD 2(USING STREAM APIs): Converting User list to UserDtoList
        List<UserDto> userDtosList = user_list.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return userDtosList;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with given Id not found"));
        UserDto userDto = entityToDto(user);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        return List.of();
    }


    private UserDto entityToDto(User savedUser) {
        UserDto userDto = UserDto.builder()
                .userId(savedUser.getUserId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .password(savedUser.getPassword())
                .about(savedUser.getAbout())
                .gender(savedUser.getGender())
                .imageName(savedUser.getImageName())
                .build();

        return userDto;
    }

    //dto to Entity
    private User dtoToEntity(UserDto userDto) {
        User user = User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .about(userDto.getAbout())
                .gender(userDto.getGender())
                .imageName(userDto.getImageName())
                .build();

        return user;
    }
}
