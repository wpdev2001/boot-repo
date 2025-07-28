package com.wp.estore.repositories;

import com.wp.estore.entities.Cart;
import com.wp.estore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,String> {
    Optional<Cart> findByUser(User user);
}
