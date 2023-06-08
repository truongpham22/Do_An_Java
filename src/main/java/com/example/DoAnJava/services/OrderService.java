package com.example.DoAnJava.services;

import com.example.DoAnJava.DTO.CreateOrdersDto;
import com.example.DoAnJava.DTO.CreateUserDto;
import com.example.DoAnJava.entity.Category;
import com.example.DoAnJava.entity.Orders;
import com.example.DoAnJava.entity.PaymentMethod;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.repository.IOrderRepository;
import com.example.DoAnJava.repository.IPaymentMethodRepository;
import com.example.DoAnJava.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;
    public List<Orders> getAllOrder(){
        return orderRepository.findAll();
    }

    public void SaveOrder(Orders order){
        orderRepository.save(order);
    }
    public void DeleteOrder(Long orderId){
        orderRepository.deleteById(orderId);
    }

    public Orders getOrdersById(Long id){
        return this.orderRepository.findById(id).orElse(null);
    }

    public Orders saveOrders(CreateOrdersDto order){
        Orders orderssave = new Orders();
       /* User user = this.userRepository.findById(order.getUser().getId()).orElse(null);
        orderssave.setUser(user);
        PaymentMethod paymentMethod = this.paymentMethodRepository.findById(order.getPaymentMethod().getId()).orElse(null);
        orderssave.setPaymentMethod(paymentMethod);
        */
        orderssave.setDiscount(order.getDiscount());
        orderssave.setStatus(order.getStatus());
        orderssave.setOrderDate(order.getOrderDate());
        orderssave.setDeliveryDate(order.getDeliveryDate());
        orderssave.setTotalPrice(order.getTotalPrice());
        return orderRepository.save(orderssave);
    }
    public Orders updateOrders(CreateOrdersDto order, Long id){
        Orders orderssave = this.orderRepository.findById(id).orElse(null);
      /*User user = this.userRepository.findById(order.getUser().getId()).orElse(null);
        orderssave.setUser(user);
        PaymentMethod paymentMethod = this.paymentMethodRepository.findById(order.getPaymentMethod().getId()).orElse(null);
        orderssave.setPaymentMethod(paymentMethod);
       */
        orderssave.setUser(order.getUser());
        orderssave.setPaymentMethod(order.getPaymentMethod());
        orderssave.setDiscount(order.getDiscount());
        orderssave.setStatus(order.getStatus());
        orderssave.setOrderDate(order.getOrderDate());
        orderssave.setDeliveryDate(order.getDeliveryDate());
        orderssave.setTotalPrice(order.getTotalPrice());
        return orderRepository.save(orderssave);

    }


}
