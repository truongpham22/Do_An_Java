package com.example.DoAnJava.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "discount")
    private int discount;

    @Column(name = "status")
    private String status;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "deliveryDate")
    private Date deliveryDate;

    @Column(name = "totalPrice")
    private BigDecimal totalPrice;

    @JsonIgnore
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "paymentMethod_id")
    private PaymentMethod paymentMethod;
}
