package com.example.DoAnJava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class KeyOrderDetail implements Serializable {
    @Column(name = "orders_id")
    Long orderId;

    @Column(name = "product_id")
    Long productId;
}
