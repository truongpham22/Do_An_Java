package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private IProductRepository productRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
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
