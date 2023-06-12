package com.example.DoAnJava.controller.admin;


import com.example.DoAnJava.DTO.CreateOrdersDto;
import com.example.DoAnJava.entity.OrderDetail;
import com.example.DoAnJava.entity.Orders;
import com.example.DoAnJava.services.OrderDetailService;
import com.example.DoAnJava.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersControllerApi {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private RestTemplate restTemplate;

    //TODO Api for OrdersController
    @GetMapping("/{id}")
    public String getView(@PathVariable(value = "id") Long id,Model model) {
        String url = "http://localhost:8080/orders/detail/"+id;
        List order = this.restTemplate.getForObject(url, List.class);
        model.addAttribute("order", order);
        return "admin/order/detail";
    }
    @GetMapping("/array")
    public String listProduct(Model model)
    {
        String url = "http://localhost:8080/orders/list";
        List orders = this.restTemplate.getForObject(url, List.class);
        model.addAttribute("orders",orders);
        return "admin/order/list";
    }
    @GetMapping("/list")
    @ResponseBody
    public List<Orders> getAllOrder() {
        List<Orders> order = this.orderService.getAllOrder();
        return order;
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public List<OrderDetail> getDetailOrders(@PathVariable Long id) {
        return this.orderDetailService.getOrderDetailsByOrdersId(id);
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
