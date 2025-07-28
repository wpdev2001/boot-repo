package com.wp.estore.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemsDto {
    private int cardItemID;
    private ProductDto product;
    private int quantity;
    private int totalPrice;
}
