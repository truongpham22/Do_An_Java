package com.example.DoAnJava.controller.admin;

import com.example.DoAnJava.DTO.CreateUserDto;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller()
@RequestMapping("/authent")
public class UserControllerApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;
    //TODO Api for UserController
    @GetMapping("/update/{id}")
    public String getView(@PathVariable(value = "id") Long id,Model model) {
        String url = "http://localhost:8080/authent/detail/"+id;
        CreateUserDto product = this.restTemplate.getForObject(url, CreateUserDto.class);
        model.addAttribute("user", product);
        return "admin/user/edit" ;
    }
    @GetMapping("/array")
    public String listProduct(Model model)
    {
        String url = "http://localhost:8080/authent/list";
        List users = this.restTemplate.getForObject(url, List.class);
        model.addAttribute("users",users);
        return "admin/user/list";
    }
    @GetMapping("/list")
    @ResponseBody
    public List<User> getUser() {
        List<User> users = this.userService.getAllUser();
        return users;
    }

    @GetMapping("/detail/{id}")
    @ResponseBody
    public User getDetaiUser(@PathVariable Long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity createUser(@ModelAttribute() CreateUserDto user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        var exist = this.userService.findUserByUserName(user.getUsername());
        if(exist != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists");
        }
        var userSave = this.userService.create(user);
        return  ResponseEntity.ok(userSave);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody CreateUserDto createUserDto) {
        User user = this.userService.getUserById(id);
        if (user != null) {
            this.userService.updateUser(createUserDto, id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
    }

    @DeleteMapping("/delete/{id}")
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
