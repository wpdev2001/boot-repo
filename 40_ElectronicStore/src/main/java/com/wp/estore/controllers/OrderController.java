package com.wp.estore.controllers;

import com.wp.estore.dtos.ApiResponseMessage;
import com.wp.estore.dtos.CreateOrderRequest;
import com.wp.estore.dtos.OrderDto;
import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    //autowiring orderService repository
    @Autowired
    private OrderService orderService;

    //create order
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        OrderDto orderDto = orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    //remove order
    @DeleteMapping
    public ResponseEntity<ApiResponseMessage> deleteOrder(@RequestParam String orderId) {
        ApiResponseMessage orderDeletedSuccessfully = ApiResponseMessage.builder()
                .message("Order deleted successfully")
                .success(true)
                .status(HttpStatus.OK)
                .build();
        orderService.removeOrder(orderId);
        return new ResponseEntity<>(orderDeletedSuccessfully, HttpStatus.OK);
    }

    //get Orders of user by userId
    // List<OrderDto> getOrderOfUser(String userId);
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getOrdersOfUser(@PathVariable String userId) {
        return new ResponseEntity<>(orderService.getOrderOfUser(userId), HttpStatus.OK);
    }

    //get all orders
    @GetMapping
    public ResponseEntity<PageableResponse<OrderDto>> getAllOrders(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "orderId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "desc", required = false) String sortDir
    ) {
        return new ResponseEntity<>(orderService.getAllOrders(pageNo, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    //update order status, order payment status, order delivered date, billingPhone and billingName based on orderId
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable String orderId, @RequestBody OrderDto orderDto) {
        //passing orderId and orderDto to service layer
        OrderDto updateOrder = orderService.updateOrder(orderId, orderDto);
        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }

}