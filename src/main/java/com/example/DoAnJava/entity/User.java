package com.example.DoAnJava.entity;

import com.example.DoAnJava.validator.annotation.ValidUsername;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {



}
