package com.wp.estore.controllers;

import com.wp.estore.dtos.ApiResponseMessage;
import com.wp.estore.dtos.CategoryDto;
import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //create
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1 = categoryService.create(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable String categoryId){
        CategoryDto updatedCategory = categoryService.update(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponseMessage> deleteCategory(@PathVariable String categoryId){
        categoryService.delete(categoryId);
        ApiResponseMessage response = ApiResponseMessage.builder()
                .message("Category deleted Successfully!!")
                .status(HttpStatus.OK)
                .success(true)
                .build();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    //get all
    @GetMapping
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title",required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    ){
        PageableResponse<CategoryDto> allCategory = categoryService.getAllCategory(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allCategory,HttpStatus.OK);
    }

    //get single
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable String categoryId){
        CategoryDto categoryById = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryById,HttpStatus.OK);
    }

    //search user using keywords
    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<CategoryDto>> searchCategoryByKeword(@PathVariable String keyword){
        List<CategoryDto> categoryDtos = categoryService.searchCategory(keyword);
        return new ResponseEntity<>(categoryDtos,HttpStatus.OK);
    }

    //upload category Image

    //serve category Image
}
