package com.wp.estore.dtos;
import lombok.*;
import jakarta.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemsDto {
    @PositiveOrZero(message = "Order item ID must be zero or positive.")
    private int orderItemId;

    @Min(value = 1, message = "Quantity must be at least 1.")
    private int quantity;

    @Min(value = 0, message = "Total price must be zero or positive.")
    private int totalPrice;

    @NotNull(message = "Product must not be null.")
    private ProductDto product;

}
