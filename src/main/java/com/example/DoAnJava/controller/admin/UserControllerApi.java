package com.example.DoAnJava.controller.admin;

import com.example.DoAnJava.DTO.CreateProductDto;
import com.example.DoAnJava.DTO.CreateUserDto;
import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller()
@RequestMapping("/authent")
public class UserControllerApi {

    @Autowired
    private UserService userService;


    //TODO Api for UserController
    @GetMapping("/list")
    @ResponseBody
    public List<User> getUser() {
        List<User> users = this.userService.getAllUser();
        return users;
    }

//    @PostMapping("/add")
//    @ResponseBody
//    public User addUser(@RequestBody CreateUserDto user) {
//
//
//        return this.userService.create(user);
//    }


    @GetMapping("/detail/{id}")
    @ResponseBody
    public User getDetaiUser(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping
    @ResponseBody
    public User createUser(@ModelAttribute() CreateUserDto user) {

        return this.userService.saveUser(user);
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody CreateUserDto createUserDto) {
        User user = this.userService.getUserById(id);
        if (user != null) {
            this.userService.updateUser(createUserDto, id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
    }

    @DeleteMapping("/Xoa/{id}")
    @ResponseBody
    public ResponseEntity DeleteUser(@PathVariable (value = "id") Long id){
        User user = this.userService.getUserById(id);
        if(user != null){
            this.userService.DeleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("delete User successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body("User not found");
    }

    // TODO Api for UserController END
}
