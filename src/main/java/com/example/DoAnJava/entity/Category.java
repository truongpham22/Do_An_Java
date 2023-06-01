package com.example.DoAnJava.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    private  ProductType productType;

    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL)
    private List<Product> products;
}
