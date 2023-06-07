package com.example.DoAnJava.controller;

import com.example.DoAnJava.entity.Product;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());

        return "user/register";
    }
    @PostMapping("/register")

    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "user/register";
        }
        user.setPassword(new
                BCryptPasswordEncoder().encode(user.getPassword()));
        userService.saveUser(user);

        return "redirect:/login";

    }

}