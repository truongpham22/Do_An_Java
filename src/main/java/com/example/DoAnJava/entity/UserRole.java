package com.example.DoAnJava.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Table(name = "user_role")
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @EmbeddedId
    private UserRolePk id;

    @ManyToOne
    @MapsId("UserId")
    @JsonManagedReference
    private User user;

    @ManyToOne
    @JsonManagedReference
    @MapsId("roleId")
    private Role role;
    // getters and setters

}

