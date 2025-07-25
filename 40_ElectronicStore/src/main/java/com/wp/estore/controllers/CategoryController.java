package com.wp.estore.controllers;

import com.wp.estore.dtos.*;
import com.wp.estore.services.CategoryService;
import com.wp.estore.services.FileService;
import com.wp.estore.services.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.catimage.path}")
    private String image_path;

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
    @PostMapping("/image/{categoryId}")
    public ResponseEntity<ImageResponse> uploadImage(
            @RequestParam MultipartFile image,
            @PathVariable String categoryId
            ) throws IOException {
        //upload file
        String category_img_file = fileService.uploadFile(image, image_path);

        //setting category file name to DB
        CategoryDto categoryById = categoryService.getCategoryById(categoryId);
        categoryById.setCoverImage(category_img_file);
        categoryService.update(categoryById,categoryId);

        ImageResponse imageResponse = ImageResponse.builder()
                .imageName(category_img_file)
                .message("Category image uploaded successfully!!!")
                .success(true)
                .status(HttpStatus.OK)
                .build();

        return new ResponseEntity<>(imageResponse,HttpStatus.OK);
    }

    //serve category Image
    @GetMapping("/image/{categoryId}")
    public void serveCategoryImage(@PathVariable String categoryId, HttpServletResponse response) throws IOException {

        CategoryDto categoryById = categoryService.getCategoryById(categoryId);
        String filename = categoryById.getCoverImage();

        InputStream resource = fileService.getResource(image_path, filename);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource,response.getOutputStream());
    }

    //create product with category
    @PostMapping("/{categoryId}/products")
    public ResponseEntity<ProductDto> productDtoResponseEntity(
            @PathVariable String categoryId,
            @RequestBody ProductDto productDto
    ){
        ProductDto createdProduct = productService.createWithCategory(productDto, categoryId);
        return new ResponseEntity<>(createdProduct,HttpStatus.CREATED);
    }

    //update category of product
    @PutMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<ProductDto> updateCategoryOfProduct(
            @PathVariable String productId,
            @PathVariable String categoryId
    ){
        ProductDto productDto = productService.updateCategory(productId, categoryId);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }

    //get products of category
    @GetMapping("/{categoryId}/products")
    public ResponseEntity<PageableResponse<ProductDto>> getProductsByCategory(
            @PathVariable String categoryId,
            @RequestParam(value = "pageNumber",defaultValue = "0",required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10",required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "productTitle",required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc",required = false) String sortDir
    ){
        PageableResponse<ProductDto> allProductsOfCategory = productService.getAllProductsOfCategory(categoryId,pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allProductsOfCategory,HttpStatus.OK);
    }
}
