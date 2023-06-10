package com.example.DoAnJava.controller;

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
    @GetMapping
    public String addBookForm(Model model){

        return  "Payment/Checkout";
    }



    @GetMapping("status-success")
    public String checkStatus(Model model){

        return  "Payment/Checkout";
    }
    @GetMapping("status-fail")
    public String fail (Model model){
        return "Payment/statusFailOrder";
    }


}
