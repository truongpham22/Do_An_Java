package com.example.DoAnJava.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItem {
    private Long id;
    private String name;
    private BigDecimal price;
    private int quantity;
    private String imageList;
    // private Product product;
    //private int quantity;
    // Constructor
}








