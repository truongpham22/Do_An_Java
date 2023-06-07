package com.example.DoAnJava.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrders {
    private Long id;
    private Integer discount;
    private String status;
    private Date orderDate;
    private Date deliveryDate;
    private Double totalPrice;
}
