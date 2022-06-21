package com.teama.bioskop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teama.bioskop.Models.Users;

public interface UsersRepository extends JpaRepository<Users,Integer>{

}
