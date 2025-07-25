package com.wp.estore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    private String productId;
    private String productTitle;
    @Column(length = 1000)
    private String productDesc;
    private int price;
    private int quantity;
    private Date addedDate;
    private boolean isLive;
    private boolean stock;
    private int discountedPrice;
    private String productImage;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    private Category category;

}
