package com.example.DoAnJava.repository;

import com.example.DoAnJava.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILocationRepository extends JpaRepository<Location,Long> {
}
