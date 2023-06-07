package com.example.DoAnJava.services;

import com.example.DoAnJava.DTO.CreateOrdersDetailDto;
import com.example.DoAnJava.DTO.CreateOrdersDto;
import com.example.DoAnJava.entity.OrderDetail;
import com.example.DoAnJava.entity.Orders;
import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.repository.IOrderDetailRepository;
import com.example.DoAnJava.repository.IOrderRepository;
import com.example.DoAnJava.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Autowired
    private IProductRepository productRepository;


    @Autowired
    private IOrderRepository orderRepository;

    public List<OrderDetail> getAllOrderDetail(){
        return orderDetailRepository.findAll();
    }

    public void SaveOderDetail(OrderDetail order){
        orderDetailRepository.save(order);
    }
    public void DeleteOrderDetail(Long orderId){
        orderDetailRepository.deleteById(orderId);
    }
    public OrderDetail getOrdersDetailById(Long id){
        return this.orderDetailRepository.findById(id).orElse(null);
    }
    public OrderDetail saveOrdersDetail(CreateOrdersDetailDto orderdetail){
        OrderDetail orderDetailsave = new OrderDetail();
     /*   Product product = this.productRepository.findById(orderdetail.getProduct().getId()).orElse(null);
        orderDetailsave.setProduct(product);
        Orders orders = this.orderRepository.findById(orderdetail.getOrders().getId()).orElse(null);
        orderDetailsave.setOrders(orders);
      */
        return orderDetailRepository.save(orderDetailsave);
    }
    public OrderDetail updateOrdersDetail(CreateOrdersDetailDto orderdetail, Long id) {
        OrderDetail orderDetailsave = this.orderDetailRepository.findById(id).orElse(null);
       /* Product product = this.productRepository.findById(orderdetail.getProduct().getId()).orElse(null);
        orderDetailsave.setProduct(product);
        Orders orders = this.orderRepository.findById(orderdetail.getOrders().getId()).orElse(null);
        orderDetailsave.setOrders(orders);
        */
        return orderDetailRepository.save(orderDetailsave);
    }

}
