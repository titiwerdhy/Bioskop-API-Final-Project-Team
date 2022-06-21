package com.teama.bioskop.Services;


import com.teama.bioskop.Models.Users;
import com.teama.bioskop.Repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class UsersService {
    private UsersRepository usersRepository;

    public List<Users> getAllUsers(){
      return this.usersRepository.findAll();
    };
}
