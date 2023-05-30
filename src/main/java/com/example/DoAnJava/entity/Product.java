package com.example.DoAnJava.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "unit")
    private String unit;

    @Column(name = "urlImageThumbnail", columnDefinition="LONGBLOB")
    private List<byte[]> urlImageThumbnail;

    @Column(name = "quantityStock")
    private Integer quantityStock;

    @OneToMany(mappedBy = "product")
    Set<OrderDetail> orderDetails;
}
