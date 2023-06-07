package com.example.DoAnJava.controller.admin;


import com.example.DoAnJava.DTO.CreateOrdersDetailDto;
import com.example.DoAnJava.DTO.CreateOrdersDto;
import com.example.DoAnJava.entity.OrderDetail;
import com.example.DoAnJava.entity.Orders;
import com.example.DoAnJava.services.OrderDetailService;
import com.example.DoAnJava.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ordersdetail")
public class OrdersDetailControllerApi {
    @Autowired
    private OrderDetailService orderDetailService;

    //TODO Api for OrdersController
    @GetMapping("/list")
    @ResponseBody
    public List<OrderDetail> getAllOrderDetail() {
        List<OrderDetail> orderDetail = this.orderDetailService.getAllOrderDetail();
        return orderDetail;
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public OrderDetail getDetailOrders(@PathVariable Long id) {
        return this.orderDetailService.getOrdersDetailById(id);
    }
    /*
    @PostMapping
    @ResponseBody
    public OrderDetail createOrdersDetail(@ModelAttribute() CreateOrdersDetailDto ordersdetail) {
        return this.orderDetailService.saveOrdersDetail(ordersdetail);
    }
    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity updateOrders(@PathVariable Long id, @RequestBody CreateOrdersDetailDto createOrdersDetailDto) {
        OrderDetail orderDetail= this.orderDetailService.getOrdersDetailById(id);
        if (orderDetail != null) {
            this.orderDetailService.updateOrdersDetail(createOrdersDetailDto, id);
            return ResponseEntity.status(HttpStatus.OK).body(orderDetail);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OrdersDetail not found");
    }
     */
    @DeleteMapping("/Xoa/{id}")
    @ResponseBody
    public ResponseEntity DeleteOrders(@PathVariable (value = "id") Long id){
        OrderDetail orderDetail = this.orderDetailService.getOrdersDetailById(id);
        if(orderDetail != null){
            this.orderDetailService.DeleteOrderDetail(id);
            return ResponseEntity.status(HttpStatus.OK).body("delete Orders successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body("OrdersDetail not found");
    }
}
