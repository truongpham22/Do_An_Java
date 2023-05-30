package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.Orders;
import com.example.DoAnJava.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
