package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.OrderDetail;
import com.example.DoAnJava.entity.Orders;
import com.example.DoAnJava.repository.IOrderDetailRepository;
import com.example.DoAnJava.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getAllOrderDetail(){
        return orderDetailRepository.findAll();
    }

    public void saveOderDetail(OrderDetail order){
        orderDetailRepository.save(order);
    }
    public void deleteOrderDetail(Long orderId){
        orderDetailRepository.deleteById(orderId);
    }
}
