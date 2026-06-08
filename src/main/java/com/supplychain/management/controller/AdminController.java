package com.supplychain.management.controller;

import com.supplychain.management.dto.UserDTO;
import com.supplychain.management.entity.User;
import com.supplychain.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    // View all users
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Delete user
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }

    // Create another admin
    @PostMapping("/create-admin")
    public UserDTO createAdmin(@RequestBody User user) {
        return userService.createAdmin(user);
    }
}