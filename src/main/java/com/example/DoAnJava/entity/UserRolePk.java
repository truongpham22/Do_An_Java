package com.example.DoAnJava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
public class UserRolePk implements Serializable {
    @Column(name = "user_id")

    private Long userId;
    @Column(name = "role_id")

    private Long roleId;
    // getters and setters
}
