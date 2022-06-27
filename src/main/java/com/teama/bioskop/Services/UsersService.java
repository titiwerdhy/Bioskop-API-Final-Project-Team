package com.teama.bioskop.Services;

import com.teama.bioskop.Helpers.DataNotFoundException;
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

    public Users getUserById(Integer id) throws DataNotFoundException {
        Optional<Users> optionalUser = this.usersRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new DataNotFoundException("User with id"+ id +"is Not Available");
        }

        return optionalUser.get();
    }

    public Users insertNewUsers(Users users){
        return this.usersRepository.save(users);
    }

    public Users updateUsersById(Users users){
        return this.usersRepository.save(users);
    }

    public void deleteUserById(Integer id) throws DataNotFoundException{
        Optional<Users> optionalUser = usersRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new DataNotFoundException("User with id"+ id +"is Not Available");
        }
        this.usersRepository.delete(optionalUser.get());
    }
}
