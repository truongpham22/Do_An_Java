package com.example.DoAnJava.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orderDetail")
public class OrderDetail {
    @EmbeddedId
    KeyOrderDetail id;
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @MapsId("ordersId")
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Column(name = "quantity")
    private Integer quantity;
}
