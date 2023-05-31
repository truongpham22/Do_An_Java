package com.example.DoAnJava.controller;

import com.example.DoAnJava.entity.Category;
import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String listProduct()
    {

        return  "product/list";
    }
    @GetMapping("/detail")
    public String addBookForm(Model model){
        Long id = 1l;
        Long catId = 2l;
        String name = "Sản phẩm test";


        Product product = new Product();
        product.setId(id);
        model.addAttribute("product", product);
        return "product/detail";
    }
}
