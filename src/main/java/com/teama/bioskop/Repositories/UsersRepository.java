package com.teama.bioskop.Repositories;

import com.teama.bioskop.Models.Users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer>{

    @Query("select u from Users u where lower(u.username) like lower(concat('%',:username,'%'))")
    public Page<Users> searchUsersByUsernamePaged(@Param("username") String username, Pageable pageable);
}
