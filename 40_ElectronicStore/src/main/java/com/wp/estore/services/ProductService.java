package com.wp.estore.services;

import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.dtos.ProductDto;


public interface ProductService {
    //create
    ProductDto createProduct(ProductDto productDto);

    //update
    ProductDto updateProduct(ProductDto productDto,String productId);

    //delete
    void deleteProduct(String productId);

    //get single product
    ProductDto getSingleProduct(String productId);

    //get all product
    PageableResponse<ProductDto> getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir);

    //get all live product
    PageableResponse<ProductDto> getAllLiveProducts(int pageNumber, int pageSize, String sortBy, String sortDir);

    //search product
    PageableResponse<ProductDto> searchProduct(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir);

    //createProductWithCategory
    ProductDto createWithCategory(ProductDto productDto, String categoryId);

    //update Category of product
    ProductDto updateCategory(String productId, String categoryId);

    //get all products of given category
    PageableResponse<ProductDto> getAllProductsOfCategory(String categoryId,int pageNumber, int pageSize, String sortBy, String sortDir);
}
