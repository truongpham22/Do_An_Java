package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.Order;
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

    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public void saveOrder(Order order){
        orderRepository.save(order);
    }
    public void deleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }
}
