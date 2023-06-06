package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;


    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

}
