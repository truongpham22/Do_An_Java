package com.example.DoAnJava.controller;

import com.example.DoAnJava.entity.Category;
import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    /*create api list products*/
    @GetMapping("/list")
    @ResponseBody
    public List<Product> getProductList(){
        List<Product> product = this.productService.getAllProduct();
        return product;
    }
    @GetMapping("/detail/{id}")
    public Product product(@PathVariable(value = "id") Long id){
        Product product = productService.getProductById(id);
        return product;
    }
    // /product/category?category=abc
    @GetMapping("/category")
    @ResponseBody
    public List<Product> getProductsByCate(@RequestParam("category") String category){
        List<Product> product = this.productService.getProductsByCategory(category);
        return product;
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Product> search(@RequestParam("search") String search){
        return  this.productService.searchProducts(search);
    }




}
