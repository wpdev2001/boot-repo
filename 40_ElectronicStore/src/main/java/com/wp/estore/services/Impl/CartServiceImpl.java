package com.wp.estore.services.Impl;

import com.wp.estore.dtos.AddItemToCartRequest;
import com.wp.estore.dtos.CartDto;
import com.wp.estore.entities.Cart;
import com.wp.estore.entities.CartItems;
import com.wp.estore.entities.Product;
import com.wp.estore.entities.User;
import com.wp.estore.exceptions.BadApiRequest;
import com.wp.estore.exceptions.ResourceNotFoundException;
import com.wp.estore.repositories.CartItemRepository;
import com.wp.estore.repositories.CartRepository;
import com.wp.estore.repositories.ProductRepository;
import com.wp.estore.repositories.UserRepository;
import com.wp.estore.services.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CartDto addItemToCart(String userId, AddItemToCartRequest cartRequest) {
        int quantity = cartRequest.getQuantity();
        String productId = cartRequest.getProductId();

        if(quantity<=0){
            throw new BadApiRequest("Quantity is lesser than or equals zero!!!");
        }

        //fetch the product
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product Not found!!!"));

        //fetch the user from db
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found!!!"));

        //checking if cart exists for user and fetching the cart else throwing exception
        Cart cart = null;
        try{
            cart = cartRepository.findByUser(user).get();

        }catch (NoSuchElementException e){

            //creating the cart
            cart = new Cart();
            cart.setCartId(UUID.randomUUID().toString());
            cart.setCreatedAt(new Date());
        }

        //perform cart operation
        //if cart item already present then update else create cart items
        AtomicReference<Boolean> updatedFlag = new AtomicReference<>(false);

        List<CartItems> cartItems = cart.getCartItems();

        cartItems = cartItems.stream().map(item -> {

            if (item.getProduct().getProductId().equals(productId)) {
                //item already present in cart just update its quantity and price
                item.setQuantity(quantity);
                item.setTotalPrice(quantity * product.getPrice());
                updatedFlag.set(true);
            }
            return item;

        }).collect(Collectors.toList());

        //cart.setCartItems(updatedItems);

        //condition to check if cart items are present or not
        if(!updatedFlag.get()){
            //create items
            CartItems cartItem = CartItems.builder()
                    .quantity(quantity)
                    .totalPrice(quantity * product.getPrice())
                    .cart(cart)
                    .product(product)
                    .build();

            cart.getCartItems().add(cartItem);
        }

        cart.setUser(user);

        Cart updatedCart = cartRepository.save(cart);

        return mapper.map(updatedCart, CartDto.class);
    }

    @Override
    public void removeItemFromCart(int cartItem) {
        CartItems cartItems = cartItemRepository.findById(cartItem).orElseThrow(() -> new ResourceNotFoundException("Cart Item not found!!!"));
        cartItemRepository.delete(cartItems);
    }

    @Override
    public void clearCart(String userId) {

        //fetch user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found!!!"));
        //fetch cart from user
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("User's cart not found!!!"));
        cart.getCartItems().clear();
        cartRepository.save(cart);

    }

    @Override
    public CartDto getCartByUser(String userId) {
        //fetch user
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not found!!!"));
        //fetch cart from user
        Cart cart = cartRepository.findByUser(user).orElseThrow(() -> new ResourceNotFoundException("User's cart not found!!!"));
        return mapper.map(cart, CartDto.class);
    }
}
