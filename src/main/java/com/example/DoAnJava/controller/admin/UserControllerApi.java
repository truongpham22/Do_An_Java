package com.example.DoAnJava.controller.admin;

import com.example.DoAnJava.DTO.CreateUserDto;
import com.example.DoAnJava.entity.Role;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.entity.UserRole;
import com.example.DoAnJava.entity.UserRolePk;
import com.example.DoAnJava.repository.IRoleRepository;
import com.example.DoAnJava.repository.IUserRepository;
import com.example.DoAnJava.repository.IUserRoleRepository;
import com.example.DoAnJava.services.RoleService;
import com.example.DoAnJava.services.UserRoleService;
import com.example.DoAnJava.services.UserService;
import com.google.api.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Controller()
@RequestMapping("/authent")
public class UserControllerApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserRoleRepository userRoleRepository;
    @Autowired
    private IRoleRepository roleRepository;

    //TODO Api for UserController
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @RequestBody User userRequest) {
        // Lấy thông tin User từ UserRepository
        User user = userRepository.findById(userId)
                .orElseThrow(null);

        // Cập nhật thông tin User
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());
        user.setName(userRequest.getName());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        // Lấy danh sách UserRole của User từ UserRoleRepository
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);

        // Tạo danh sách UserRole mới để cập nhật
        List<UserRole> updatedUserRoles = new ArrayList<>();

        // Thêm các UserRole mới được yêu cầu vào danh sách cập nhật
        for (UserRole roleId : userRequest.getUser_roles()) {
            Role role = roleRepository.findById(roleId.getRole().getId())
                    .orElseThrow(null);
            updatedUserRoles.add(userRepository.addRoleToUser(user.getId(), role.getId()));
        }

        // Xóa các UserRole không còn được yêu cầu khỏi danh sách UserRole của User
        for (UserRole userRole : userRoles) {
            if (!userRequest.getUser_roles().contains(userRole.getRole().getId())) {
                userRoleRepository.delete(userRole);
            }
        }

        // Thêm các UserRole mới được yêu cầu vào UserRole của User
        userRoleRepository.saveAll(updatedUserRoles);

        // Lưu thông tin User đã cập nhật vào UserRepository
        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(updatedUser);
    }
    @GetMapping("/update/{id}")
    public String getView(@PathVariable(value = "id") Long id,Model model) {
        String url = "http://localhost:8080/authent/detail/"+id;
        CreateUserDto product = this.restTemplate.getForObject(url, CreateUserDto.class);
        model.addAttribute("user", product);
        List<Role> roles = this.roleService.getAllRole();
        model.addAttribute("roles", roles);
        List<UserRole> userRoles = this.userRoleService.getUserRoleByUserId(product.getId());
        model.addAttribute("userRoles", userRoles);
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
