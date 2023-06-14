package com.example.DoAnJava.DTO;


import com.example.DoAnJava.entity.Location;
import com.example.DoAnJava.entity.Orders;
import com.example.DoAnJava.entity.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String phoneNumber;
    private String roleName;
    private Long location_id;
    private String address;
    private String district;
    private String ward;
}
