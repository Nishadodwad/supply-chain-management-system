package com.supplychain.management.controller;

import com.supplychain.management.dto.ApiResponse;
import com.supplychain.management.dto.AuthResponse;
import com.supplychain.management.entity.User;
import com.supplychain.management.dto.UserDTO;
import com.supplychain.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public ApiResponse<List<UserDTO>> getAllUsers() {
        return new ApiResponse<>(
                true,
                "Users fetched successfully",
                userService.getAllUsers()
        );
    }
    @PostMapping("/login")
    public AuthResponse login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }
}