package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductTypeRepository extends JpaRepository<ProductType, Long> {
}