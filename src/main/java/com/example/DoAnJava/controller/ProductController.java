package com.example.DoAnJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping
    public String listProduct()
    {

        return  "product/list";
    }
    @GetMapping("/detail")
    public String addBookForm(Model model){
        return "product/detail";
    }
}
