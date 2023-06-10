package com.example.DoAnJava.controller;

import com.example.DoAnJava.DTO.CreateProductDto;
import com.example.DoAnJava.DTO.ProductDto;
import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.services.FirebaseService;
import com.example.DoAnJava.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;


import javax.swing.plaf.PanelUI;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private FirebaseService firebaseService;
    @Autowired
    private RestTemplate restTemplate;

    /* TODO create api list products*/
    /*products by category*/
    @GetMapping("/cate/{name}")
    public String listProductByCate(@PathVariable(value="name") String name,Model model)
    {
        String url = "http://localhost:8080/product/category/"+name;
        List products = this.restTemplate.getForObject(url, List.class);
        model.addAttribute("products", products);
        String uri = "http://localhost:8080/category";
        List categories = this.restTemplate.getForObject(uri, List.class);
        model.addAttribute("categories",categories);
        return "product/list";
    }
    @GetMapping("/products")
    public String listProduct(Model model)
    {
        String url = "http://localhost:8080/product/list";
        List products = this.restTemplate.getForObject(url, List.class);
        model.addAttribute("products",products);
        String uri = "http://localhost:8080/category";
        List categories = this.restTemplate.getForObject(uri, List.class);
        model.addAttribute("categories",categories);
        return "product/list";
    }
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Product product = this.productService.getProductById(id);
        if (product != null) {
            this.productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.OK).body("delete product successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body("Product not found");
    }
    @PutMapping("/edit/{id}")
    @ResponseBody
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody CreateProductDto createProductDto) {
        Product product = this.productService.getProductById(id);
        if (product != null) {
            this.productService.updateProduct(createProductDto, id);
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found");
    }

    /*TODO test call api here */

    @GetMapping("/view/{id}")
    public String getView(@PathVariable(value = "id") Long id,Model model) {
        String url = "http://localhost:8080/product/"+id;
        ProductDto product = this.restTemplate.getForObject(url, ProductDto.class);
        model.addAttribute("product", product);
        return "product/detail";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Product> getProductList() {
        List<Product> product = this.productService.getAllProduct();
        logger.error("đôi khi con người ta hay buồn do lỗi nhiều quá");

        return product;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product product(@PathVariable(value = "id") Long id) {
        Product product = productService.getProductById(id);
        return product;
    }

    // /product/category?category=abc
    @GetMapping("/category/{name}")
    @ResponseBody
    public List<Product> getProductsByCate(@PathVariable(value="name") String category) {
        List<Product> product = this.productService.getProductsByCategory(category);
        return product;
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Product> search(@RequestParam("name") String search) {
        return this.productService.searchProducts(search);
    }
    //upsoluong theo tên sản phẩm
    @PostMapping("/updatesoluong/{id}")
    @ResponseBody
    public ResponseEntity updatesoluong(@PathVariable Long id, @RequestBody CreateProductDto createProductDto){
        Product products = this.productService.getProductById(id);
        if (products != null) {
            this.productService.updateSoLuong(createProductDto, id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(products);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found");
    }
    //end
    @PostMapping()
    @ResponseBody
    public Product create(@ModelAttribute() CreateProductDto product) {
        String url = this.firebaseService.uploadImages(product.getFile()).get(0);
        System.out.println("url 0  " + url);
        List<String> url_list = this.firebaseService.uploadImages(product.getFiles());
        product.setUrlImageThumbnail(url);
//        String result = String.join(",", url_list);
        product.setImageList(url_list);
        System.out.println(url_list);
        return this.productService.saveProduct(product);
    }
    /* TODO create api list products END*/



}
