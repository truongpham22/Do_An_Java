package com.example.DoAnJava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private String imageList;

    @Column(name = "quantityStock")
    private Integer quantityStock;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = true)
    private Category category;


    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "product_type_id", referencedColumnName = "id", nullable = true)
    private ProductType productType;

    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
}
