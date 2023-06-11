package com.example.DoAnJava.controller;

import com.example.DoAnJava.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.DoAnJava.entity.Orders;


@Controller
@RequestMapping("/pay")
public class PaymentController {
    @Autowired
    private CartService cartService;
    @GetMapping
    public String addBookForm(Model model){

        return  "Payment/Checkout";
    }
    @GetMapping("status-success")
    public String checkStatus(Model model, HttpSession session){
        cartService.removeCart(session);
        return  "Payment/Checkout";
    }
    @GetMapping("status-fail")
    public String fail (Model model,  HttpSession session){
        cartService.removeCart(session);
        return "Payment/statusFailOrder";
    }




}
