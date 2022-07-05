package com.teama.bioskop.RestControllers;


import com.teama.bioskop.DTOs.UsersRequestDTO;
import com.teama.bioskop.DTOs.UsersResponseDTO;
import com.teama.bioskop.Handlers.ResponseHandler;
import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Users;

import com.teama.bioskop.Services.UsersService;
import lombok.AllArgsConstructor;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UsersRestController {
    private final UsersService usersService;

    private static final Logger logger = LogManager.getLogger(UsersRestController.class);

    /***
     * Get all data from Users table
     * @return List of Users
     */
    @GetMapping("/users")
    public ResponseEntity<Object> getAll() {
        try {
            List<Users> users = this.usersService.getAllUsers();
            logger.info("Get All Users: " + users);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, users);
        } catch (Exception e) {
            logger.error("Users table doesn't have any data!");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    /***
     * Get one User by Id
     * @param id of selected Users
     * @return Selected User data 
     * @throws DataNotFoundException
     */
    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) throws DataNotFoundException{
        try {
            Users user = this.usersService.getUserById(id);
            UsersResponseDTO usersResponseDTO = user.convertToResponse();
            logger.info("Get User by Id: " + user);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, usersResponseDTO);
        } catch (DataNotFoundException e) {
            logger.error("Cannot find User with Id " + id);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    /***
     * Insert new data to User table
     * @param users new users data
     * @return new User
     */
    @PostMapping("/user")
    public ResponseEntity<Object> insert(@RequestBody UsersRequestDTO usersRequestDTO){
        try {
            Users newUser = usersService.insertNewUsers(usersRequestDTO.convertToUsers());
            logger.info("Create new User: " + newUser);
            return ResponseHandler.generateResponse("Data successfully inserted!", HttpStatus.OK, newUser);
        } catch (Exception e) {
            logger.error("Cannot create into user: " + usersRequestDTO);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    /***
     * Update data to User table
     * @param users Users which going to be updated
     * @return updated users
     * @throws DataNotFoundException
     */
    @PutMapping("/user")
    public ResponseEntity<Object> update(@RequestBody UsersRequestDTO usersRequestDTO) throws DataNotFoundException{
        try {
            Users updatedUser = usersService.updateUsersById(usersRequestDTO.updateUsers());
            logger.info("Update User: " + updatedUser);
            return ResponseHandler.generateResponse("Data successfully updated", HttpStatus.OK, updatedUser);
        } catch (DataNotFoundException e) {
            logger.error("Cannot update user: " + usersRequestDTO);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    /***
     * Delete data to User table
     * @param id Users which going to be deleted
     * @return response status
     * @throws DataNotFoundException
     */
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Integer id) throws DataNotFoundException{
        try {
            Users deletedUsers = usersService.getUserById(id);
            usersService.deleteUserById(id);
            logger.info("Delete User: " + deletedUsers);
            return ResponseHandler.generateResponse("Successfully delete user!", HttpStatus.OK, deletedUsers);
        } catch (DataNotFoundException e) {
            logger.error("Cannot delete user with ID " + id);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);

        }
    }
}