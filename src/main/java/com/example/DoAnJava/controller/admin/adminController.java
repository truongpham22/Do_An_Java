package com.example.DoAnJava.controller.admin;

import com.example.DoAnJava.entity.Category;
import com.example.DoAnJava.entity.ProductType;
import com.example.DoAnJava.services.CategoryService;
import com.example.DoAnJava.services.ProductTypeService;
import org.springframework.ui.Model;
import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductTypeService productTypeService;


    @GetMapping
    public String home()
    {
        return  "admin/index";
    }
    @GetMapping("/product")
    public String listProduct(Model model)
    {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "admin/product/list";
    }


    @GetMapping("/{id}/edit")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);
        List<Category> categories = categoryService.getAllCate();
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("productTypes", productTypes);
        return "admin/product/edit";
    }
}
