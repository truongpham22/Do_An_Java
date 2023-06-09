package com.example.DoAnJava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class KeyOrderDetail implements Serializable {
    @Column(name = "orders_id")
    Long orderId;

    @Column(name = "product_id")
    Long productId;
}
