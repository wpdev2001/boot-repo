package com.wp.estore.dtos;
import com.wp.estore.entities.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemsDto {
    private int orderItemId;
    private int quantity;
    private int totalPrice;
    private Product product;
    private OrderDto order;
}
