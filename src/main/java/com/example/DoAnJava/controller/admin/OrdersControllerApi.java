package com.example.DoAnJava.controller.admin;


import com.example.DoAnJava.DTO.CreateOrdersDto;
import com.example.DoAnJava.DTO.CreateUserDto;
import com.example.DoAnJava.entity.Orders;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.services.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersControllerApi {

    @Autowired
    private OrderService orderService;

    //TODO Api for OrdersController
    @GetMapping("/list")
    @ResponseBody
    public List<Orders> getAllOrder() {
        List<Orders> order = this.orderService.getAllOrder();
        return order;
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public Orders getDetailOrders(@PathVariable Long id) {
        return this.orderService.getOrdersById(id);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity createOrders(@RequestBody() CreateOrdersDto orders) {
        boolean isSuccess = this.orderService.createOrder(orders);
        System.out.println("GỌI " + isSuccess);
        if (isSuccess) {
            return ResponseEntity.ok(isSuccess);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(isSuccess) ;
    }



}
