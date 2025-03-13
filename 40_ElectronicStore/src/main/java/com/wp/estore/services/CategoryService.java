package com.wp.estore.services;

import com.wp.estore.dtos.CategoryDto;
import com.wp.estore.dtos.PageableResponse;

import java.util.List;

public interface CategoryService {
    //create
    CategoryDto create(CategoryDto categoryDto);

    //update
    CategoryDto update(CategoryDto categoryDto,String categoryId);

    //delete
    void delete(String categoryId);

    //get all
    PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get single category detail
    CategoryDto getCategoryById(String categoryId);

    List<CategoryDto> searchCategory(String keyword);

    //search
}
