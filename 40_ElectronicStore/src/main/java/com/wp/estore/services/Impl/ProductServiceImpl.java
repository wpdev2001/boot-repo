package com.wp.estore.services.Impl;

import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.dtos.ProductDto;
import com.wp.estore.entities.Product;
import com.wp.estore.exceptions.ResourceNotFoundException;
import com.wp.estore.helper.Helper;
import com.wp.estore.repositories.ProductRepository;
import com.wp.estore.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        //converting Dto to entity
        Product product = mapper.map(productDto, Product.class);
        Product savedProduct = productRepository.save(product);

        //converting entity to Dto
        return mapper.map(savedProduct,ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String productId) {

        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with given Id not found!!!"));
        product.setProductTitle(productDto.getProductTitle());
        product.setProductDesc(productDto.getProductDesc());
        product.setPrice(productDto.getPrice());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        product.setQuantity(productDto.getQuantity());
        product.setLive(productDto.isLive());
        product.setStock(productDto.isStock());
        Product updatedProduct = productRepository.save(product);
        return mapper.map(updatedProduct, ProductDto.class);

    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with given Id not found!!!"));
        productRepository.delete(product);
    }

    @Override
    public ProductDto getSingleProduct(String productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with given Id not found!!!"));
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);

        Page<Product> page = productRepository.findAll(pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(page, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public PageableResponse<ProductDto> getAllLiveProducts(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        Page<Product> byIsLiveTrue_page = productRepository.findByIsLiveTrue(pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(byIsLiveTrue_page, ProductDto.class);
        return pageableResponse;

    }

    @Override
    public PageableResponse<ProductDto> searchProduct(String keyword, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        Page<Product> byProductTitleContaining_page = productRepository.findByProductTitleContaining(keyword, pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(byProductTitleContaining_page, ProductDto.class);
        return pageableResponse;
    }

}
