package com.wp.estore.services.Impl;

import com.wp.estore.dtos.CreateOrderRequest;
import com.wp.estore.dtos.OrderDto;
import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.entities.*;
import com.wp.estore.exceptions.BadApiRequest;
import com.wp.estore.exceptions.ResourceNotFoundException;
import com.wp.estore.helper.Helper;
import com.wp.estore.repositories.CartRepository;
import com.wp.estore.repositories.OrderRepository;
import com.wp.estore.repositories.UserRepository;
import com.wp.estore.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
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
    public OrderDto createOrder(CreateOrderRequest orderDto) {

        //fetch userId and cartId from createOrderRequest
        String userId = orderDto.getUserId();
        String cartId = orderDto.getCartId();
        //fetch user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        //fetch cart
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        List<CartItems> cartItems = cart.getCartItems();

        if(cartItems.size()<=0){
            throw new BadApiRequest("Invalid number of items in cart");
        }

        Order order = Order.builder()
                .billingName(orderDto.getBillingName())
                .billingPhone(orderDto.getBillingPhone())
                .billingAddress(orderDto.getBillingAddress())
                .orderedDate(new Date())
                .deliveredDate(null)
                .paymentStatus(orderDto.getPaymentStatus())
                .orderStatus(orderDto.getOrderStatus())
                .orderId(UUID.randomUUID().toString())
                .user(user)
                .build();

        //set orderItems, orderAmount

        //intializing order amount to zero first
        AtomicReference<Integer> orderAmount = new AtomicReference<>(0);

        List<OrderItems> orderItemsList = cartItems.stream().map(cartItem -> {
            OrderItems orderItems = OrderItems.builder()
                    .quantity(cartItem.getQuantity())
                    .product(cartItem.getProduct())
//                    .totalPrice(cartItem.getTotalPrice()) --> why not this??
                    .totalPrice(cartItem.getQuantity() * cartItem.getProduct().getPrice())
                    .order(order)
                    .build();

            orderAmount.set(orderAmount.get() + orderItems.getTotalPrice());
            return orderItems;
        }).collect(Collectors.toList());

        order.setOrderItems(orderItemsList);
        order.setOrderAmount(orderAmount.get());

        //clear cart once we get the order from the cart
        cart.getCartItems().clear();
        cartRepository.save(cart);

        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder,OrderDto.class);
    }

    @Override
    public void removeOrder(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getOrderOfUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        List<Order> orders = orderRepository.findByUser(user);
        List<OrderDto> orderDtos = orders.stream().map(order -> modelMapper.map(order, OrderDto.class)).collect(Collectors.toList());
        return orderDtos;
    }

    @Override
    public PageableResponse<OrderDto> getAllOrders(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Order> page = orderRepository.findAll(pageable);
        return Helper.getPageableResponse(page,OrderDto.class);
    }

    @Override
    public OrderDto updateOrder(String orderId, OrderDto orderDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        /**
         * Only non-null and (for strings) non-empty fields from
         * orderDto will update order; null or empty fields are
         * ignored and existing values in order remain unchanged.
         */
        BeanUtils.copyProperties(orderDto, order, Helper.getNullOrEmptyPropertyNames(orderDto));
        // save the updated order
        orderRepository.save(order);
        return modelMapper.map(order, OrderDto.class);
    }
}
