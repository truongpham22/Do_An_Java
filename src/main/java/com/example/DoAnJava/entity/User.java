package com.example.DoAnJava.entity;

import com.example.DoAnJava.validator.annotation.ValidUsername;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "Username must be less than 50 characters")
    @Column(name = "username", unique = true)
    @ValidUsername
    private String username;


    @Column(name = "password", length = 250, nullable = false)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "email", length = 50)
    @Size(max = 50, message = "Email must be less than 50 characters")
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    @Size(max = 50, message = "Your name must be less than 50 characters")
    @NotBlank(message = "Your name is required")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Orders> orders;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    @JsonIgnore
    private Set<UserRole> user_roles;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = true)
    private Location location;

    @Column(name = "address")
    private String address;
    @Column(name = "district")
    private String district;
    @Column(name = "ward")
    private String ward;


}

