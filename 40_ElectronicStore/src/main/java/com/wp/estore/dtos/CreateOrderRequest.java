package com.wp.estore.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateOrderRequest {
    @NotBlank(message = "CartId cannot be blank")
    private String cartId;
    @NotBlank(message = "UserId cannot be blank")
    private String userId;
    private String orderStatus="PENDING"; //setting a default value
    private String paymentStatus="NOT PAID";
    @NotBlank(message = "billing address cannot be blank")
    private String billingAddress;
    @NotBlank(message = "billing phone cannot be blank")
    private String billingPhone;
    @NotBlank(message = "billing name cannot be blank")
    private String billingName;
}
