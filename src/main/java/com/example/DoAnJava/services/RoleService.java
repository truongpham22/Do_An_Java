package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.Role;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.repository.IRoleRepository;
import com.example.DoAnJava.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private IRoleRepository roleRepository;

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    public void saveRole(Role role){
        roleRepository.save(role);
    }
    public void deleteRole(Long roleId){
        roleRepository.deleteById(roleId);
    }
}
