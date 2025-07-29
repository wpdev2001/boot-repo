package com.wp.estore.repositories;

import com.wp.estore.entities.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItems,Integer> {
}
