package com.example.DoAnJava.DTO;

import com.example.DoAnJava.entity.Category;
import com.example.DoAnJava.entity.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String unit;
    private String urlImageThumbnail;
    private String imageList;
    private Integer quantityStock;
    private Category category;
    private ProductType productType;
}
