package com.wp.estore.services.Impl;

import com.wp.estore.dtos.CategoryDto;
import com.wp.estore.dtos.PageableResponse;
import com.wp.estore.entities.Category;
import com.wp.estore.exceptions.ResourceNotFoundException;
import com.wp.estore.helper.Helper;
import com.wp.estore.repositories.CategoryRepository;
import com.wp.estore.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper mapper;

    @Value("${user.profile.catimage.path}")
    private String imagePath;

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public CategoryDto create(CategoryDto categoryDto) {

        //generating category Id automatically using UUID
        String categoryId = UUID.randomUUID().toString();
        categoryDto.setCategoryId(categoryId);

        Category category = mapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        CategoryDto categoryDto1 = mapper.map(savedCategory, CategoryDto.class);
        return categoryDto1;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        //get category of given id and setting the values
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with given id not found!!"));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());

        //updating it in database
        Category updatedCategory = categoryRepository.save(category);

        //converting category to CategoryDto
        CategoryDto categoryDto1 = mapper.map(updatedCategory, CategoryDto.class);
        return categoryDto1;
    }

    @Override
    public void delete(String categoryId) {
        //get category first with the given categoryId
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with given id not found"));

        //delete category image related to category
        String fullPath = imagePath + category.getCoverImage();

        try{
            Path path = Paths.get(fullPath);
            Files.delete(path);

        }catch (NoSuchFileException e){
            logger.info("No image found at given location!!!");
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        categoryRepository.delete(category);

    }

    @Override
    public PageableResponse<CategoryDto> getAllCategory(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);
        return pageableResponse;

    }

    @Override
    public CategoryDto getCategoryById(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category with given id not found"));
        return mapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> searchCategory(String keyword) {
        List<Category> categories_list = categoryRepository.findByTitleContaining(keyword);

        //Throwing exception if category list is empty
        if(categories_list.isEmpty()){
            throw new ResourceNotFoundException("No category found for the searched keyword");
        }

        List<CategoryDto> categoryDtoList = categories_list.stream()
                .map(category -> mapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return categoryDtoList;
    }

}
