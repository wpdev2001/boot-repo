package com.wp.estore.services;
import com.wp.estore.dtos.CreateOrderRequest;
import com.wp.estore.dtos.OrderDto;
import com.wp.estore.dtos.PageableResponse;
import java.util.List;

public interface OrderService {
    //create order
    OrderDto createOrder(CreateOrderRequest createOrderRequest);

    //remove order
    void removeOrder(String orderId);

    //get Orders of user
    List<OrderDto> getOrderOfUser(String userId);

    //get orders
    PageableResponse<OrderDto> getAllOrders(int pageNo, int pageSize, String sortBy, String sortDir);


    OrderDto updateOrder(String orderId, OrderDto orderDto);
}
