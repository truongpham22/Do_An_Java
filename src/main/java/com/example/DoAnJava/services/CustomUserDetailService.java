package com.example.DoAnJava.services;
import com.example.DoAnJava.entity.CustomUserDetail;
import com.example.DoAnJava.entity.User;
import com.example.DoAnJava.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

