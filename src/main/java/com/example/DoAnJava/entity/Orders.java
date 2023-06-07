package com.example.DoAnJava.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "paymentMethod_id")
    private PaymentMethod paymentMethod;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "status")
    private String status;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "deliveryDate")
    private Date deliveryDate;

    @Column(name = "totalPrice")
    private Double totalPrice;

    @JsonIgnore
    @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

}
