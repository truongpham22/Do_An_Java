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
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text", nullable = true)
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "unit")
    private String unit;

    @Column(name = "urlImageThumbnail", columnDefinition="text", nullable = true)
    private String urlImageThumbnail;

    @Column(name = "imageList", columnDefinition="text")
    private List<String> imageList;

    @Column(name = "quantityStock")
    private Integer quantityStock;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
}
