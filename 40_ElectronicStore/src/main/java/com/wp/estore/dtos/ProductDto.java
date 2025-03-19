package com.wp.estore.dtos;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String productId;
    private String productTitle;
    private String productDesc;
    private int price;
    private int quantity;
    private Date addedDate;
    private boolean isLive;
    private boolean stock;
    private int discountedPrice;
}
