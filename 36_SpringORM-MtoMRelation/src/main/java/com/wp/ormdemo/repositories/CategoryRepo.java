package com.wp.ormdemo.repositories;

import com.wp.ormdemo.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
