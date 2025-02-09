package com.wp.ormdemo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Product {
    @Id
    private int pid;
    private String productName;

    @ManyToMany(mappedBy = "productList", fetch = FetchType.EAGER)
    private List<Category> categoryList = new ArrayList<>();

    public Product() {
    }

    public Product(int pid, String productName, List<Category> categoryList) {
        this.pid = pid;
        this.productName = productName;
        this.categoryList = categoryList;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
