package com.example.DoAnJava.services;

import com.example.DoAnJava.DTO.CreateUserDto;
import com.example.DoAnJava.entity.*;
import com.example.DoAnJava.repository.IRoleRepository;
import com.example.DoAnJava.repository.IUserRepository;
import com.example.DoAnJava.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRoleRepository userroleRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return this.userRepository.findById(id).orElse(null);
    }

//    public User create(CreateUserDto user){
//        User usertoSave = new User();
//        usertoSave.setUsername(user.getUsername());
//        usertoSave.setPassword(user.getPassword());
//        usertoSave.setEmail(user.getEmail());
//        usertoSave.setName(user.getName());
//        if(user.getRoleName() == null){
//            user.setRoleName("USER");
//        }
//        Long role_id = this.roleRepository.getRoleIdByName(user.getRoleName());
//        Role role = this.roleRepository.findById(role_id).orElse(null);
//        usertoSave.setRole(role);
//        usertoSave.setPhoneNumber(user.getPhoneNumber());
//        return userRepository.save(usertoSave);
//    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void DeleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public User saveUsers(CreateUserDto user){

        User usersave = new User();
        usersave.setUsername(user.getUsername());
        usersave.setPassword(user.getPassword());
        usersave.setEmail(user.getEmail());
        usersave.setName(user.getName());
        Role role = this.roleRepository.findById(2L).orElse(null);
        usersave.setRole(role);
        usersave.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(usersave);
    }
    public User updateUser(CreateUserDto user, Long id){
        User usersave = this.userRepository.findById(id).orElse(null);
        usersave.setUsername(user.getUsername());
        usersave.setPassword(user.getPassword());
        usersave.setEmail(user.getEmail());
        usersave.setName(user.getName());
        Role role = this.roleRepository.findById(user.getRole().getId()).orElse(null);
        usersave.setRole(role);
        usersave.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(usersave);
    }

    public void save(User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if(roleId !=0 && userId != 0){
            userRepository.addRoleToUser(userId, roleId);
        }
    }

}
