package com.wp.ormdemo;


import com.wp.ormdemo.entities.Category;
import com.wp.ormdemo.entities.Product;
import com.wp.ormdemo.repositories.CategoryRepo;
import com.wp.ormdemo.repositories.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ProductRepo productRepo;

	Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//MANY TO MANY
		/*
		Product product1 = new Product();
		product1.setPid(11);
		product1.setProductName("Samsung TV");

		Product product2 = new Product();
		product2.setPid(12);
		product2.setProductName("Samsung 23 Ultra");

		Product product3 = new Product();
		product3.setPid(13);
		product3.setProductName("Iphone 15 pro");

		Category category1 = new Category();
		category1.setCid(101);
		category1.setTitle("Electronics");

		Category category2 = new Category();
		category2.setCid(102);
		category2.setTitle("Mobile Phones");

		List<Product> category1Products = category1.getProductList();
		category1Products.add(product1);
		category1Products.add(product2);
		category1Products.add(product3);

		List<Product> category2Products = category2.getProductList();
		category2Products.add(product1);
		category2Products.add(product2);

		categoryRepo.save(category1);
		categoryRepo.save(category2);

		 */

		//Getting the stored records

		//Getting how many products belongs to category with given category Id
		Category category1 = categoryRepo.findById(101).get();
		System.out.println(category1.getProductList().size());

		Category category2 = categoryRepo.findById(102).get();
		System.out.println(category2.getProductList().size());

		//Getting how many categories belongs to product with given product Id
		Product product1 = productRepo.findById(11).get();
		System.out.println(product1.getCategoryList().size());



	}
}
