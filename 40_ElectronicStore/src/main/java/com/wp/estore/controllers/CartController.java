package com.wp.estore.controllers;

import com.wp.estore.dtos.AddItemToCartRequest;
import com.wp.estore.dtos.ApiResponseMessage;
import com.wp.estore.dtos.CartDto;
import com.wp.estore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    //add item to cart
    @PostMapping("/{userId}")
    public ResponseEntity<CartDto> addItemToCart(
            @RequestBody AddItemToCartRequest request,
            @PathVariable String userId
    ){
        CartDto cartDto = cartService.addItemToCart(userId, request);
        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

    //remove item from cart
    @DeleteMapping("/{cartItemID}")
    public ResponseEntity<ApiResponseMessage> removeItemFromCart(
            @PathVariable int cartItemID
    ){
        cartService.removeItemFromCart(cartItemID);
        ApiResponseMessage cartItemRemovedSuccessfully = ApiResponseMessage.builder()
                .message("Cart item removed successfully")
                .status(HttpStatus.OK)
                .success(true)
                .build();
        return new ResponseEntity<>(cartItemRemovedSuccessfully,HttpStatus.OK);
    }

    //clear cart
    @DeleteMapping("/clear/{userID}")
    public ResponseEntity<ApiResponseMessage> clearCartItems(
            @PathVariable String userID
    ){
        cartService.clearCart(userID);
        ApiResponseMessage cartItemRemovedSuccessfully = ApiResponseMessage.builder()
                .message("Cart for the user: " + userID + " cleared successfully")
                .status(HttpStatus.OK)
                .success(true)
                .build();
        return new ResponseEntity<>(cartItemRemovedSuccessfully,HttpStatus.OK);
    }

    //get cart by user
    @GetMapping("/{userID}")
    public ResponseEntity<CartDto> getCartByUser(
            @PathVariable String userID
    ){
        CartDto cartByUser = cartService.getCartByUser(userID);
        return new ResponseEntity<>(cartByUser,HttpStatus.OK);
    }
}
