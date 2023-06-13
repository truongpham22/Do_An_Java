package com.example.DoAnJava.services;

import com.example.DoAnJava.entity.Category;
import com.example.DoAnJava.entity.Location;
import com.example.DoAnJava.repository.ICategoryRepository;
import com.example.DoAnJava.repository.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private ILocationRepository locationRepository;
    public List<Location> getAllLoaction(){
        return locationRepository.findAll();
    }
}
