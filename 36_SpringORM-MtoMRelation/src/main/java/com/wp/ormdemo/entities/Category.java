package com.wp.ormdemo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Category {
    @Id
    private int cid;
    private String title;

    //@ManyToMany(mappedBy = "categoryList",cascade = CascadeType.ALL) --> we're not saving the data acc to this field
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Product> productList = new ArrayList<>();

    public Category() {
    }

    public Category(int cid, String title, List<Product> productList) {
        this.cid = cid;
        this.title = title;
        this.productList = productList;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
