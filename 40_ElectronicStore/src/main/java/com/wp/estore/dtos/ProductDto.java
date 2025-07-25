package com.wp.estore.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String productId;
    @Size(min = 3,max = 12,message = "Invalid Product Title")
    @NotBlank
    private String productTitle;
    @Size(min = 3,max = 22,message = "Invalid Product Desc")
    @NotBlank
    private String productDesc;
    @NotBlank
    private int price;
    @NotBlank
    private int quantity;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotBlank
    private Date addedDate;
    private boolean isLive;
    private boolean stock;
    private int discountedPrice;
    private String productImage;
    private CategoryDto category;
}
