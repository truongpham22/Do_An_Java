package com.example.DoAnJava.controller.admin;

import com.example.DoAnJava.DTO.CreateProductDto;
import com.example.DoAnJava.DTO.ProductDto;
import com.example.DoAnJava.entity.Category;
import com.example.DoAnJava.entity.ProductType;
import com.example.DoAnJava.services.CategoryService;
import com.example.DoAnJava.services.ProductTypeService;
import org.springframework.ui.Model;
import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class adminProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private RestTemplate restTemplate;

   @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") Long id)
    {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping
    public String home()
    {
        return  "admin/index";
    }
    @GetMapping("/products")
    public String listProduct(Model model)
    {
        String url = "http://localhost:8080/product/list";
        List products = this.restTemplate.getForObject(url, List.class);
        model.addAttribute("products",products);
        return "admin/product/list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model){
        model.addAttribute("product", new CreateProductDto());
        model.addAttribute("categories", categoryService.getAllCate());
        model.addAttribute("types", productTypeService.getAllProductTypes());
        return "admin/product/add";
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
    @GetMapping("/product/{id}")
    public String getView(@PathVariable(value = "id") Long id,Model model) {
        String url = "http://localhost:8080/product/"+id;
        ProductDto product = this.restTemplate.getForObject(url, ProductDto.class);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }
}
