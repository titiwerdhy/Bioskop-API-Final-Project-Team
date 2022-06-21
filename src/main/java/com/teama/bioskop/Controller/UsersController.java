package com.teama.bioskop.Controller;


import com.teama.bioskop.Models.Users;

import com.teama.bioskop.Services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/users")
    public List<Users> getAll() {
        return this.usersService.getAllUsers();

    }
}