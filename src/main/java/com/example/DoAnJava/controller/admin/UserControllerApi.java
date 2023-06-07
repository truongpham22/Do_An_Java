package com.example.DoAnJava.controller.admin;

import com.example.DoAnJava.DTO.CreateUserDto;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@RequestMapping("/authent")
public class UserControllerApi {

    @Autowired
    private UserService userService;


    //TODO Api for UserController
    @GetMapping
    @ResponseBody
    public List<User> getUsers() {
        return this.userService.getAllUser();
    }
    @GetMapping("/detail/{id}")
    @ResponseBody
    public User getDetaiUser(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

//    @PostMapping("/add")
//    @ResponseBody
//    public User addUser(@RequestBody CreateUserDto user) {
//
//
//        return this.userService.create(user);
//    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Long id) {
        User user = this.userService.getUserById(id);
        if (user != null) {
            this.userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("delete User successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST ).body("User not found");
    }

    // TODO Api for UserController END
}
