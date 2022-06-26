package com.teama.bioskop.Controllers;


import com.teama.bioskop.DTOs.UsersRequestDTO;
import com.teama.bioskop.DTOs.UsersResponseDTO;
import com.teama.bioskop.Helpers.DataNotFoundException;
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

@RestController
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    /***
     * Get all data from Users table
     * @return List of Users
     */
    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAll() {
        List<Users> users = this.usersService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    /***
     * Get one User by Id
     * @param id of selected Users
     * @return Selected User data 
     * @throws DataNotFoundException
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<UsersResponseDTO> getById(@PathVariable("id") Integer id) throws DataNotFoundException{
        Users user = this.usersService.getUserById(id);
        UsersResponseDTO usersResponseDTO = user.convertToResponse();
        return ResponseEntity.status(HttpStatus.OK).body(usersResponseDTO);
    }

    /***
     * Insert new data to User table
     * @param users new users data
     * @return new User
     */
    @PostMapping("/user")
    public ResponseEntity<Users> insert(@RequestBody UsersRequestDTO usersRequestDTO){
        Users newUser = usersService.insertNewUsers(usersRequestDTO.convertToUsers());
        return ResponseEntity.status(HttpStatus.OK).body(newUser);
    }

    /***
     * Update data to User table
     * @param users Users which going to be updated
     * @return updated users
     */
    @PutMapping("/user")
    public ResponseEntity<Users> update(@RequestBody UsersRequestDTO usersRequestDTO){
        Users updatedUser = usersService.updateUsersById(usersRequestDTO.updateUsers());
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }

    /***
     * Delete data to User table
     * @param id Users which going to be deleted
     * @return response status
     * @throws DataNotFoundException
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Users> deleteById(@PathVariable("id") Integer id) throws DataNotFoundException{
        Users deletedUsers = usersService.getUserById(id);
        usersService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedUsers);
    }
}