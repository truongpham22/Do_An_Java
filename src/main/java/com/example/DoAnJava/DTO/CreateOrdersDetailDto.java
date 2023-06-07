package com.example.DoAnJava.DTO;


import com.example.DoAnJava.entity.KeyOrderDetail;
import com.example.DoAnJava.entity.Orders;
import com.example.DoAnJava.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrdersDetailDto {
    KeyOrderDetail id;
    private Product product;
    private Orders orders;
}
