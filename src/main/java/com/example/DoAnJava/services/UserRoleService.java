package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.OrderDetail;
import com.example.DoAnJava.entity.UserRole;
import com.example.DoAnJava.repository.IOrderRepository;
import com.example.DoAnJava.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService {
    @Autowired
    private IUserRoleRepository userRoleRepository;
    public List<UserRole> getUserRoleByUserId(Long ordersId) {
        return userRoleRepository.findByUserId(ordersId);
    }
}
