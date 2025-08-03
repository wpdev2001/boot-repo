package com.wp.estore.repositories;

import com.wp.estore.entities.Order;
import com.wp.estore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,String> {
    List<Order> findByUser(User user);
}
