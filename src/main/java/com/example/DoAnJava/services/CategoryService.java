package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.Category;
import com.example.DoAnJava.entity.PaymentMethod;
import com.example.DoAnJava.repository.ICategoryRepository;
import com.example.DoAnJava.repository.IPaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    public List<Category> getAllCate(){
        return categoryRepository.findAll();
    }

    public void saveCate(Category category){
        categoryRepository.save(category);
    }
    public void deleteCate(Long cateId){
        categoryRepository.deleteById(cateId);
    }
}
