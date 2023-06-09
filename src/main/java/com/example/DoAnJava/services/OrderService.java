package com.example.DoAnJava.services;

import com.example.DoAnJava.DTO.AddCartToOrderDto;
import com.example.DoAnJava.DTO.CreateOrdersDto;
import com.example.DoAnJava.DTO.CreateUserDto;
import com.example.DoAnJava.entity.*;
import com.example.DoAnJava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private IOrderRepository orderRepository;
    @Autowired
    private IOrderDetailRepository orderDetailRepository;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IPaymentMethodRepository paymentMethodRepository;
    public List<Orders> getAllOrder(){
        return orderRepository.findAll();
    }
    public Orders getOrdersById(Long id){
        return this.orderRepository.findById(id).orElse(null);
    }

    public boolean createOrder(CreateOrdersDto order){
        try {
            Orders orderToSave = new Orders();
            orderToSave.setDiscount(order.getDiscount());
            orderToSave.setStatus(order.getStatus());
            orderToSave.setOrderDate(new Date());
            orderToSave.setDeliveryDate(new Date());
            orderToSave.setTotalPrice(order.getTotalPrice());
            User user = this.userRepository.findById(order.getUserId()).orElse(null);
            orderToSave.setUser(user);
            PaymentMethod paymentMethod = this.paymentMethodRepository.findById(order.getPaymentMethodId()).orElse(null);
            orderToSave.setPaymentMethod(paymentMethod);
             orderRepository.save(orderToSave); // done create order
            // order details
            for (AddCartToOrderDto item: order.getCartItems()
            ) {
                OrderDetail detail = new OrderDetail();
                KeyOrderDetail keyOrderDetail = new KeyOrderDetail();
                Product product = this.productRepository.findById(item.getProductId()).orElse(null);
                detail.setProduct(product);
                Orders orders = orderRepository.findById(orderToSave.getId()).orElse(null);
                keyOrderDetail.setOrderId(orderToSave.getId());
                assert product != null;
                keyOrderDetail.setProductId(product.getId());
                detail.setId(keyOrderDetail);
                detail.setOrders(orders);
                detail.setQuantity(item.getQuantity());
                System.out.println("trước khi SAVE");

                this.orderDetailRepository.save(detail);
                System.out.println("sau khi SAVE");

                if(product != null) {
                    product.setQuantityStock(product.getQuantityStock() - item.getQuantity());
                    this.productRepository.save(product);// update sl
                }

            }
            return true;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        //


    }



}
