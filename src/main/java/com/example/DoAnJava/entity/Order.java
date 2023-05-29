package com.example.DoAnJava.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetail;
}
