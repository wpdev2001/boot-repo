package com.wp.estore.repositories;

import com.wp.estore.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItems,Integer> {
}
