package com.wp.estore.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderItemId;
    private int quantity;
    private int totalPrice;
    @OneToOne
    private Product product;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "orders")
    private Order order;
}
