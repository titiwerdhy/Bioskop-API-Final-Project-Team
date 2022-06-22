package com.teama.bioskop.Services;

import com.teama.bioskop.Models.Users;
import com.teama.bioskop.Repositories.UsersRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UsersService {
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return this.usersRepository.findAll();
    }

    public Optional<Users> getUserById(Integer id) {
        return this.usersRepository.findById(id);
    }

    public Users insertNewUsers(Users users){
        return this.usersRepository.save(users);
    }

    public Users updateUsersById(Users users){
        return this.usersRepository.save(users);
    }

    public void deleteUserById(Integer id){
        Users deleteUser = usersRepository.getReferenceById(id);
        this.usersRepository.delete(deleteUser);
    }
}
