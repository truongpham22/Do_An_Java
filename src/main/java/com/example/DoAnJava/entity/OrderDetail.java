package com.example.DoAnJava.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orderDetail")
public class OrderDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "product_price",referencedColumnName = "price")
    private Product productPrice;

}
