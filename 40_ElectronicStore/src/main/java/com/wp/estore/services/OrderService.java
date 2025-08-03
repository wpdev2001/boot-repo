package com.wp.estore.services;
import com.wp.estore.dtos.OrderDto;
import com.wp.estore.dtos.PageableResponse;
import java.util.List;

public interface OrderService {
    //create order
    OrderDto createOrder(OrderDto orderDto,String userId, String cartId);

    //remove order
    void removeOrder(String orderId);

    //get Orders of user
    List<OrderDto> getOrderOfUser(String userId);

    //get orders
    PageableResponse<OrderDto> getAllOrders(int pageNo, int pageSize, String sortBy, String sortDir);


}
