package com.wp.estore.services;

import com.wp.estore.dtos.AddItemToCartRequest;
import com.wp.estore.dtos.CartDto;

public interface CartService {
    // 1. create cart if not exists and add the items
    // 2. if cart exists, then simply add the items
    CartDto addItemToCart(String userId, AddItemToCartRequest cartRequest);

    //remove item from cart
    void removeItemFromCart(int cartItem);

    //clear all items from cart
    void clearCart(String userId);

    CartDto getCartByUser(String userId);


}
