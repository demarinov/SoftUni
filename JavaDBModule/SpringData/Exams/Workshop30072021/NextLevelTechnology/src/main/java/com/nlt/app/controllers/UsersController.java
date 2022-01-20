package com.nlt.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {


    @GetMapping("/register")
    public String registerUser() {
        return "user/register";
    }

    @GetMapping({"/login","/logout"})
    public String loginUser() {
        return "user/login";
    }
}
