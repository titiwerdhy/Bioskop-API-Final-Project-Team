package com.teama.bioskop.Controllers;

// import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Users;
import com.teama.bioskop.Services.UsersService;


import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UsersController {
    @Autowired
    private final UsersService usersService;

    /***
     * CRUD Users
     * @param model
     * @return
     */
    @GetMapping("/crud/users/{pageNo}")
    public String getAll(Model model, @PathVariable("pageNo") int pageNo){
        int pageSize = 10;
        Page<Users> page = usersService.getAllUsersPaged(pageNo, pageSize);
        List<Users> userList = page.getContent();
        // Collections.reverse(userList);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("users", userList);
        model.addAttribute("newUser", new Users());
        model.addAttribute("updateUser", new Users());
        return "users-crud";
    }

    /***
     * Create new User
     * @param model
     * @param users
     * @return
     */
    @PostMapping("/create/user")
    public String createNew(Model model, @ModelAttribute Users users){
        usersService.insertNewUsers(users);
        return "redirect:/crud/users";
    }

    /***
     * Update user
     * @param model
     * @param users
     * @return
     * @throws DataNotFoundException
     */
    @PutMapping("/update/user/{id}")
    public String updateById(Model model, @PathVariable("id") Integer id, @ModelAttribute Users users) throws DataNotFoundException{
        users.setUserId(id);
        usersService.updateUsersById(users);
        return "redirect:/crud/users";
    }

    /***
     * Delete User
     * @param model
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @DeleteMapping("/delete/user/{id}")
    public String deleteById(Model model, @PathVariable("id") Integer id) throws DataNotFoundException{
        usersService.deleteUserById(id);
        return "redirect:/crud/users";
    }
}
