package com.canx.postapp.controller;

import com.canx.postapp.dto.UserDTO;
import com.canx.postapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public UserDTO addUser(@RequestBody @Valid UserDTO user) {
        return userService.addUser(user);
    }
}
