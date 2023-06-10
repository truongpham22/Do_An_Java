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

    public CreateUserDto parseCreateUserDto(User user){
        CreateUserDto createUser = new CreateUserDto();
        createUser.setName(user.getName());
        createUser.setPassword(user.getPassword());
        createUser.setUsername(user.getUsername());
        createUser.setPhoneNumber(user.getPhoneNumber());
        createUser.setEmail(user.getEmail());
        return createUser;
    }
    public User create(CreateUserDto user){
        User usertoSave = new User();
        usertoSave.setUsername(user.getUsername());
        usertoSave.setPassword(user.getPassword());
        usertoSave.setEmail(user.getEmail());
        usertoSave.setName(user.getName());
        usertoSave.setPhoneNumber(user.getPhoneNumber());
        var k = userRepository.save(usertoSave);
        if(user.getRoleName() == null){
            UserRole userRole = new UserRole();
            UserRolePk pk = new UserRolePk();
            pk.setUserId(k.getId());
            var id = this.roleRepository.getRoleIdByName("USER");
            var role = this.roleRepository.findById(id).orElse(null);
            pk.setRoleId(id);
            userRole.setId(pk);
            userRole.setRole(role);
            userRole.setUser(usertoSave);
            this.userroleRepository.save(userRole);
        }
        return  usertoSave;
    }

    public void DeleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public User updateUser(CreateUserDto user, Long id){
        User usersave = this.userRepository.findById(id).orElse(null);
        usersave.setUsername(user.getUsername());
        usersave.setPassword(user.getPassword());
        usersave.setEmail(user.getEmail());
        usersave.setName(user.getName());
      // TODO tao quyen
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
