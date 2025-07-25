package com.wp.estore.repositories;

import com.wp.estore.entities.Category;
import com.wp.estore.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {
    //search
    Page<Product> findByProductTitleContaining(String keyword,Pageable pageable);
    Page<Product> findByIsLiveTrue(Pageable pageable);
    Page<Product> findByCategory(Category category, Pageable pageable);
}
