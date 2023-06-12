package com.example.DoAnJava.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditRoleDto {
    private Long userId;
    private Long oldRoleId;
    private Long newRoleId;

}
