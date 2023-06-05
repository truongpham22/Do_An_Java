package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.ProductType;
import com.example.DoAnJava.repository.IProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    @Autowired
    private IProductTypeRepository productTypeRepository;

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public Optional<ProductType> getProductTypeById(Long id) {
        return productTypeRepository.findById(id);
    }

    public ProductType saveProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    public ProductType updateProductType(Long id, ProductType productType) {
        Optional<ProductType> optionalProductType = productTypeRepository.findById(id);
        if (optionalProductType.isPresent()) {
            productType.setId(id);
            return productTypeRepository.save(productType);
        } else {
            return null;
        }
    }

    public boolean deleteProductType(Long id) {
        Optional<ProductType> optionalProductType = productTypeRepository.findById(id);
        if (optionalProductType.isPresent()) {
            productTypeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}