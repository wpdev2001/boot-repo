package com.wp.estore.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private String categoryId;
    @NotBlank(message = "Title required!!!")
    @Size(min=4, max=10, message = "title must be of minimum 4 characters!!!")
    //@Min(value = 4, message = "title must be of minimum 4 characters!!") --> Why this doesn't works?
    private String title;
    @NotBlank(message = "Description required!!!")
    private String description;
    private String coverImage;

}
