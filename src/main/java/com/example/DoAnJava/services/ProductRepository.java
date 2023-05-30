package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.entity.Role;
import com.example.DoAnJava.repository.IProductRepository;
import com.example.DoAnJava.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRepository {
    @Autowired
    private IProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public void saveProduct(Product product){
        productRepository.save(product);
    }
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
}
