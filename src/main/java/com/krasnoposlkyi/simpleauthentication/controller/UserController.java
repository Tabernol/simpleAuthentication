package com.krasnoposlkyi.simpleauthentication.controller;

import com.krasnoposlkyi.simpleauthentication.dto.UserPostDto;
import com.krasnoposlkyi.simpleauthentication.entity.User;
import com.krasnoposlkyi.simpleauthentication.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String test(){
        return "test String";
    }

    @PostMapping
    public User add(@RequestBody UserPostDto user) {
        return userService.add(user);

    }
}
