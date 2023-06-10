package com.example.DoAnJava.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/errors")
public class CustomErrorController {
    @GetMapping
    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if(status !=null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == 404)
                return "error/404";
        }
        return null;
    }
}