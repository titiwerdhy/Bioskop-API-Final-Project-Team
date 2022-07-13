package com.teama.bioskop.Services;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Users;
import com.teama.bioskop.Repositories.UsersRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersService {
    private UsersRepository usersRepository;

    public List<Users> getAllUsers(){
        return this.usersRepository.findAll();
    }

    public Page<Users> getAllUsersPaged(String username, int pageNo, int pageSize, String sort, String order){
        Pageable pageable;
        if (sort == null) {
            if (username == null) {
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("updatedAt").ascending());
            }else{
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("updated_at").ascending());
            }
        }else{
            if (order.equals("ascending")) {
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sort).ascending());
            }else{
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sort).descending());
            }
        }
        if (username == null) {
            return this.usersRepository.findAll(pageable);
        }else{
            return this.usersRepository.searchUsersByUsernamePaged(username, pageable);
        }
    }

    public Users getUserById(Integer id) throws DataNotFoundException {
        Optional<Users> optionalUser = this.usersRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new DataNotFoundException("User with id "+ id +" is not exist!");
        }

        return optionalUser.get();
    }

    public Users insertNewUsers(Users users){
        return this.usersRepository.save(users);
    }

    public Users updateUsersById(Users users) throws DataNotFoundException{
        Optional<Users> updateUser = usersRepository.findById(users.getUserId());
        if(updateUser.isEmpty()){
            throw new DataNotFoundException("User with id " + users.getUserId() + " is not exist!");
        }
        return this.usersRepository.save(users);
    }

    public void deleteUserById(Integer id) throws DataNotFoundException{
        Optional<Users> optionalUser = usersRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new DataNotFoundException("User with id "+ id +" is not exist!");
        }
        this.usersRepository.delete(optionalUser.get());
    }
}
