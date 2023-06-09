package com.example.DoAnJava.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddCartToOrderDto {
    private int quantity;
    private Long productId;

}
