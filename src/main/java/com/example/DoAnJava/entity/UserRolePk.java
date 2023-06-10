package com.example.DoAnJava.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserRolePk implements Serializable {
    @Column(name = "user_id")

    private Long userId;
    @Column(name = "role_id")

    private Long roleId;
}
