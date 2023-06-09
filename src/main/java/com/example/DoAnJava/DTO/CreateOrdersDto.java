package com.example.DoAnJava.DTO;

import com.example.DoAnJava.entity.PaymentMethod;
import com.example.DoAnJava.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrdersDto {
    private Long userId;
    private Long paymentMethodId;
    private int discount;
    private String status;
    private BigDecimal totalPrice;
    private List<AddCartToOrderDto> cartItems;
}
