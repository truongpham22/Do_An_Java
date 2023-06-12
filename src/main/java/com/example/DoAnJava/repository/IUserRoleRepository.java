package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.Role;
import com.example.DoAnJava.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("SELECT ur.id.roleId FROM UserRole ur WHERE ur.id.userId = :userId")
    List<Long> findRoleIdsByUserId(@Param("userId") Long userId);
}
