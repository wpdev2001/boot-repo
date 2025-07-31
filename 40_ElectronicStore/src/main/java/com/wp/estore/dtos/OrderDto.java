package com.wp.estore.dtos;
import lombok.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private String orderId;
    private String orderStatus="PENDING"; //setting a default value
    private String paymentStatus="NOT PAID";
    private int orderAmount;
    private String billingAddress;
    private String billingPhone;
    private String billingName;
    private Date orderedDate;
    private Date deliveredDate;
    private UserDto user;
    private List<OrderItemsDto> orderItems = new ArrayList<>();
}
