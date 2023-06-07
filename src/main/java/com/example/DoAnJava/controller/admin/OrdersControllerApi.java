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
    public Orders createOrders(@ModelAttribute() CreateOrdersDto orders) {
        return this.orderService.saveOrders(orders);
    }
    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity updateOrders(@PathVariable Long id, @RequestBody CreateOrdersDto createOrdersDto) {
        Orders orders = this.orderService.getOrdersById(id);
        if (orders != null) {
            this.orderService.updateOrders(createOrdersDto, id);
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Orders not found");
    }
    @DeleteMapping("/Xoa/{id}")
    @ResponseBody
    public ResponseEntity DeleteOrders(@PathVariable (value = "id") Long id){
        Orders orders = this.orderService.getOrdersById(id);
        if(orders != null){
            this.orderService.DeleteOrder(id);
            return ResponseEntity.status(HttpStatus.OK).body("delete Orders successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body("Orders not found");
    }


}
