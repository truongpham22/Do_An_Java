package com.example.DoAnJava.services;

import com.example.DoAnJava.controller.ProductController;
import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.repository.IProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<Product> getAllProduct(){
        return (List<Product>) productRepository.findAll();
    }
    public Product getProductById(Long id){
        Optional<Product> optional = productRepository.findById(id);
        return  optional.orElse(null);
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
}
