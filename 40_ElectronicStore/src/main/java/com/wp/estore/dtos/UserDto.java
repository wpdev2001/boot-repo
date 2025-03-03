package com.wp.estore.dtos;

import com.wp.estore.validate.ImageNameValid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userId;

    @Size(min = 3, max=35, message = "Invalid name !!")
    private String name;

//    @Email(message = "Invalid user Email!!") --> Doesn't validate email properly
//  @Pattern
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$")
    @NotBlank(message = "provide email!!!")
    private String email;

    @NotBlank(message = "Password is required!!!")
    private String password;

    @Size(min=4, max=6, message = "Invalid Gender!!!")
    private String gender;

    @NotBlank(message = "write something about yourself!!!")
    private String about;

    //Custom validator
    @ImageNameValid
    private String imageName;

}
