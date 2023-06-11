package com.example.DoAnJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Controller

@RequestMapping("/check")
public class CheckController {
    @GetMapping
    public String checkout(Model model)
    {

        return  "Payment/Checkout";
    }
}
