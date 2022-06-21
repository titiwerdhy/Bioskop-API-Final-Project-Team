package com.teama.bioskop.Controllers;


import com.teama.bioskop.Models.Users;

import com.teama.bioskop.Services.UsersService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    /***
     * Get all data from Users table
     * @return List of Users
     */
    @GetMapping("/users")
    public List<Users> getAll() {
        return this.usersService.getAllUsers();

    }

    /***
     * Get one User by Id
     * @param id of selected Users
     * @return Selected User data 
     */
    @GetMapping("/user/{id}")
    public Optional<Users> getById(@PathVariable("id") Integer id){
        return this.usersService.getUserById(id);
    }

    /***
     * Insert new data to User table
     * @param users new users data
     * @return new User
     */
    @PostMapping("/user")
    public Users insert(@RequestBody Users users){
        usersService.insertNewUsers(users);
        return users;
    }

    /***
     * Update data to User table
     * @param users Users which going to be updated
     * @return updated users
     */
    @PutMapping("/user")
    public Users update(@RequestBody Users users){
        usersService.updateUsersById(users.getUserId());
        return users;
    }

    /***
     * Delete data to User table
     * @param id Users which going to be deleted
     * @return response status
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Optional<Users>> deleteById(@PathVariable("id") Integer id){
        Optional<Users> deletedUsers = usersService.getUserById(id);
        usersService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedUsers);
    }
}