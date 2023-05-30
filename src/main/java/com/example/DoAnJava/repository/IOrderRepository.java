package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Orders,Long> {
}
