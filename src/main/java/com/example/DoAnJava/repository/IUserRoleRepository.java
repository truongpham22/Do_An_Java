package com.example.DoAnJava.repository;
import org.springframework.transaction.annotation.Transactional;


import com.example.DoAnJava.entity.Role;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("SELECT ur.id.roleId FROM UserRole ur WHERE ur.id.userId = :userId")
    List<Long> findRoleIdsByUserId(@Param("userId") Long userId);
    @Modifying
    @Transactional
    @Query("UPDATE UserRole ur SET ur.role.id = :roleIdNew WHERE ur.user.id = :userId AND ur.role.id = :roleIdOld")
    void editRoleOfUser(@Param("userId") Long userId, @Param("roleIdOld") Long roleIdOld, @Param("roleIdNew") Long roleIdNew);
}
