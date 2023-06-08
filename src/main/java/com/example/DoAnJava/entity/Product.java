package com.example.DoAnJava.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private BigDecimal price;

    @Column(name = "unit")
    private String unit;

    @Column(name = "urlImageThumbnail", columnDefinition = "text", nullable = true)
    private String urlImageThumbnail;

    //    @Column(name = "imageList", columnDefinition="text")
//    private String imageList;
    @ElementCollection
    @Column(name = "imageList", length = 1000)
    private List<String> imageList;

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
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;
}
