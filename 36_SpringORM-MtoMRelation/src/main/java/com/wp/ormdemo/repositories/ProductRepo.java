package com.wp.ormdemo.repositories;

import com.wp.ormdemo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Integer> {
}
