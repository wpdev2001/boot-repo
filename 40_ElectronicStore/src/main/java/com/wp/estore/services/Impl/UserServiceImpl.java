package com.wp.estore.services.Impl;

import com.wp.estore.controllers.UserController;
import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.dtos.UserDto;
import com.wp.estore.entities.User;
import com.wp.estore.exceptions.ResourceNotFoundException;
import com.wp.estore.helper.Helper;
import com.wp.estore.repositories.UserRepository;
import com.wp.estore.services.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${user.profile.image.path}")
    private String imagePath;

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

        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Todo with given Id not found"));
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
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Todo with given Id not found"));

        //delete user profile image
        String fullPath = imagePath + user.getImageName();

        try {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        } catch (NoSuchFileException e) {
            logger.info("User image not found in folder");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //delete user
        userRepository.delete(user);
    }

    @Override
    public PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir) {

        //MEHTHOD 2(USING STREAM APIs): Converting User list to UserDtoList

        //Using ternary operator to sort the data according to fieldName and Direction
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort); //Using Pagination and sorting
        Page<User> page = userRepository.findAll(pageable);

        PageableResponse<UserDto> pageableResponse = Helper.getPageableResponse(page, UserDto.class); // we have used Stream API concept inside Helper.java
        return pageableResponse;


        //METHOD 1(NORMAL APPROACH): Converting User list to UserDtoList
//        List<UserDto> userDtosList = new ArrayList<>();
//
//        for(User user : user_list){
//            UserDto userDto = entityToDto(user);
//            userDtosList.add(userDto);
//        }
//        return userDtosList;
    }

    @Override
    public UserDto getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given Id not found"));
        UserDto userDto = entityToDto(user);
        return userDto;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User with given Email doesn't exists"));
        return entityToDto(user);
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);
        List<UserDto> userDtos = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    //MODEL MAPPER APPROACH
    //Converting entity -> Dto

    private UserDto entityToDto(User savedUser){
        return modelMapper.map(savedUser, UserDto.class);
    }

    //converting Dto -> entity
    private User dtoToEntity(UserDto userDto){
        return modelMapper.map(userDto,User.class);
    }


    //MANUAL APPROACH
    //entity to dto
//    private UserDto entityToDto(User savedUser) {
//        UserDto userDto = UserDto.builder()
//                .userId(savedUser.getUserId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName())
//                .build();
//
//        return userDto;
//    }
//
//    //dto to Entity
//    private User dtoToEntity(UserDto userDto) {
//        User user = User.builder()
//                .userId(userDto.getUserId())
//                .name(userDto.getName())
//                .email(userDto.getEmail())
//                .password(userDto.getPassword())
//                .about(userDto.getAbout())
//                .gender(userDto.getGender())
//                .imageName(userDto.getImageName())
//                .build();
//
//        return user;
//    }
}
