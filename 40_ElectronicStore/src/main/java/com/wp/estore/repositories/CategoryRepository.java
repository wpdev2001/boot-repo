package com.wp.estore.repositories;

import com.wp.estore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,String> {
    List<Category> findByTitleContaining(String keyword);
}
