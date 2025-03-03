package com.codecool.server.controller;

import com.codecool.server.DTO.user.NewUserDTO;
import com.codecool.server.DTO.user.UserDTO;
import com.codecool.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

@GetMapping("/{userId}")
    public UserDTO getUserById(@RequestParam long userId) {
        return userService.getUserById(userId);
}
@PostMapping
    public long addUser(@RequestBody NewUserDTO newUserDTO) {
        return userService.createUser(newUserDTO);
}

@PatchMapping
    public long updateUser(@RequestBody UserDTO userDTO) {
        return userService.modifyUser(userDTO);
}

@DeleteMapping("/{userId}")
    public boolean deleteUserById(@RequestParam long userId) {
        return userService.deleteUser(userId);
}
}
