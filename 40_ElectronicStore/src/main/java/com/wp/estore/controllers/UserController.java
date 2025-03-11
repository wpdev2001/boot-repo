package com.wp.estore.controllers;

import com.wp.estore.dtos.ApiResponseMessage;
import com.wp.estore.dtos.ImageResponse;
import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.dtos.UserDto;
import com.wp.estore.services.FileService;
import com.wp.estore.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath; //This field we will get through configuration file

    //create User
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto userDto1 = userService.createUser(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    //update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @Valid @RequestBody UserDto newUserDetails){
        UserDto userDto = userService.updateUser(newUserDetails, userId);
        return ResponseEntity.ok(userDto);
    }

    //delete
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage
                .builder()
                .message("User deleted Successfully!!!")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    //get all users
    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name",required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    ){
        return new ResponseEntity<>(userService.getAllUser(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    //get single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId){
        return new ResponseEntity<>(userService.getUserById(userId),HttpStatus.OK);
    }

    //get user by email
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }

    //search user using keywords
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords){
        return new ResponseEntity<>(userService.searchUser(keywords),HttpStatus.OK);
    }

    //upload user image
    @PostMapping("/image/{userId}")
    public ResponseEntity<ImageResponse> uploadUserImage(
            @RequestParam("userImage")MultipartFile image,
            @PathVariable String userId
            ) throws IOException {

        String imageName = fileService.uploadFile(image, imageUploadPath);

        //saving imageName for the user who uploaded the Image in DB
        UserDto user = userService.getUserById(userId);
        user.setImageName(imageName);
        userService.updateUser(user,userId);

        ImageResponse response = ImageResponse.builder()
                .imageName(imageName)
                .message("userImage uploaded!!!")
                .status(HttpStatus.CREATED)
                .success(true)
                .build();

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    //serve user image
    @GetMapping("/image/{userId}")
    public void serveUserImage(@PathVariable String userId, HttpServletResponse response) throws IOException {
        UserDto userById = userService.getUserById(userId);
        logger.info("User image name: {}",userById.getImageName());
        InputStream resource = fileService.getResource(imageUploadPath, userById.getImageName());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
