package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.Orders;
import com.example.DoAnJava.entity.PaymentMethod;
import com.example.DoAnJava.repository.IOrderRepository;
import com.example.DoAnJava.repository.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository orderRepository;

    public List<Orders> getAllOrder(){
        return orderRepository.findAll();
    }

    public void SaveOrder(Orders order){
        orderRepository.save(order);
    }
    public void DeleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }




}
