package com.example.DoAnJava.services;

import com.example.DoAnJava.DTO.CreateUserDto;
import com.example.DoAnJava.entity.*;
import com.example.DoAnJava.repository.ILoactionRepository;
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
    private ILoactionRepository loactionRepository;
    @Autowired
    private IUserRoleRepository userroleRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public CreateUserDto parseCreateUserDto(User user) {
        CreateUserDto createUser = new CreateUserDto();
        createUser.setName(user.getName());
        createUser.setPassword(user.getPassword());
        createUser.setUsername(user.getUsername());
        createUser.setPhoneNumber(user.getPhoneNumber());
        createUser.setEmail(user.getEmail());
        return createUser;
    }

    public User create(CreateUserDto user) {
        User usertoSave = new User();
        usertoSave.setUsername(user.getUsername());
        usertoSave.setPassword(user.getPassword());
        usertoSave.setEmail(user.getEmail());
        usertoSave.setName(user.getName());
        usertoSave.setPhoneNumber(user.getPhoneNumber());
        var k = userRepository.save(usertoSave);
        if (user.getRoleName() == null) {
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
        } else {
            UserRole userRole = new UserRole();
            UserRolePk pk = new UserRolePk();
            pk.setUserId(k.getId());
            var id = this.roleRepository.getRoleIdByName(user.getRoleName());
            var role = this.roleRepository.findById(id).orElse(null);
            pk.setRoleId(id);
            userRole.setId(pk);
            userRole.setRole(role);
            userRole.setUser(usertoSave);
            this.userroleRepository.save(userRole);
        }
        return usertoSave;
    }

    public void DeleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public User updateUser(CreateUserDto user, Long id) {

        User usersave = this.userRepository.findById(id).orElse(null);
        System.out.println(" USER ID ă"+ usersave.getId());

        usersave.setUsername(user.getUsername());
        usersave.setPassword(user.getPassword());
        usersave.setEmail(user.getEmail());
        usersave.setName(user.getName());
        usersave.setPhoneNumber(user.getPhoneNumber());
        usersave.setDistrict(user.getDistrict());
        usersave.setAddress(user.getAddress());
        usersave.setWard(user.getWard());
        System.out.println(" LOCATION ID "+ user.getLocation_id());

        Location location = this.loactionRepository.findById(user.getLocation_id()).orElse(null);
        System.out.println(" LOCATION ID find"+ location);

        usersave.setLocation(location);
        var k = userRepository.save(usersave);
        System.out.println("DEBUG USER "+ k);
        UserRole userRole = new UserRole();
        UserRolePk pk = new UserRolePk();
        pk.setUserId(id);
        var roleId = this.roleRepository.getRoleIdByName(user.getRoleName());
        var role = this.roleRepository.findById(roleId).orElse(null);
        pk.setRoleId(roleId);
        userRole.setId(pk);
        System.out.println("ROLE " + role);
        userRole.setRole(role);
        userRole.setUser(k);
        System.out.println("USER ROLE " + userRole);

        this.userroleRepository.save(userRole);
        return k;
    }

    public User findUserByUserName(String userName) {
        return this.userRepository.findByUsername(userName);
    }


    public void save(User user) {
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUsername(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if (roleId != 0 && userId != 0) {
            userRepository.addRoleToUser(userId, roleId);
        }
    }

}
