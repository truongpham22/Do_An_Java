package com.example.DoAnJava.controller;

import com.example.DoAnJava.DTO.CreateUserDto;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.repository.IProductRepository;
import com.example.DoAnJava.repository.IUserRepository;
import com.example.DoAnJava.repository.IUserRoleRepository;
import com.example.DoAnJava.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;

    @GetMapping("/login")
    public String login() {
        return "home/dangnhap";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());

        return "home/dangky";
    }

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity test(Model model) {
        List<Long> kq = this.userRoleRepository.findRoleIdsByUserId(3L);
        return ResponseEntity.ok(kq) ;
    }
    @PostMapping("/register")

    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "home/dangky";
        }
        user.setPassword(new
                BCryptPasswordEncoder().encode(user.getPassword()));
        CreateUserDto userDto = this.userService.parseCreateUserDto(user);
        userService.create(userDto);
        return "home/dangnhap";

    }

}