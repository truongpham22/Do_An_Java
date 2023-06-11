package com.example.DoAnJava.services;

import com.example.DoAnJava.daos.Cart;
import jakarta.servlet.http.HttpSession;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {
    private static final String USER_SESSION_KEY = "user";
    public void setUserId(HttpSession session, Long userId) {
        session.setAttribute(USER_SESSION_KEY, userId);
    }

    public Long getUserId(HttpSession session) {
        return (Long) session.getAttribute(USER_SESSION_KEY);
    }

}
