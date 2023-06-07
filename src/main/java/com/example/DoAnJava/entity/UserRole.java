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

    public UserRolePk getId() {
        return id;
    }

    public void setId(UserRolePk id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    // getters and setters

}

