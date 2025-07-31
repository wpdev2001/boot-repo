package com.wp.estore.services.Impl;

import com.wp.estore.dtos.OrderDto;
import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.entities.Cart;
import com.wp.estore.entities.CartItems;
import com.wp.estore.entities.Order;
import com.wp.estore.entities.User;
import com.wp.estore.exceptions.BadApiRequest;
import com.wp.estore.exceptions.ResourceNotFoundException;
import com.wp.estore.repositories.CartRepository;
import com.wp.estore.repositories.OrderRepository;
import com.wp.estore.repositories.UserRepository;
import com.wp.estore.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto, String userId, String cartId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        List<CartItems> cartItems = cart.getCartItems();

        if(cartItems.size()<=0){
            throw new BadApiRequest("Invalid number of items in cart");
        }

        Order order = Order.builder()
                .billingName(orderDto.getBillingName())
                .billingPhone(orderDto.getBillingPhone())
                .billingAddress(orderDto.getBillingAddress())
                .orderedDate(orderDto.getOrderedDate())
                .deliveredDate(orderDto.getDeliveredDate())
                .paymentStatus(orderDto.getPaymentStatus())
                .orderStatus(orderDto.getOrderStatus())
                .orderId(UUID.randomUUID().toString())
                .user(user)
                .build();

        return modelMapper.map(order,OrderDto.class);
    }

    @Override
    public void removeOrder(String orderId) {

    }

    @Override
    public List<OrderDto> getOrderOfUser(String userId) {
        return List.of();
    }

    @Override
    public PageableResponse<OrderDto> getAllOrders(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }
}
