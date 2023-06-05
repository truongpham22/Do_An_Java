package com.example.DoAnJava.controller;

import com.example.DoAnJava.entity.ProductType;
import com.example.DoAnJava.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productTypes")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        if (productTypes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(productTypes, HttpStatus.OK);
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductType> getProductTypeById(@PathVariable Long id) {
        Optional<ProductType> optionalProductType = productTypeService.getProductTypeById(id);
        if (optionalProductType.isPresent()) {
            return new ResponseEntity<>(optionalProductType.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductType> saveProductType(@RequestBody ProductType productType) {
        ProductType savedProductType = productTypeService.saveProductType(productType);
        return new ResponseEntity<>(savedProductType, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductType> updateProductType(@PathVariable Long id,
                                                         @RequestBody ProductType productType) {
        ProductType updatedProductType = productTypeService.updateProductType(id, productType);
        if (updatedProductType != null) {
            return new ResponseEntity<>(updatedProductType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProductType(@PathVariable Long id) {
        boolean isDeleted = productTypeService.deleteProductType(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}