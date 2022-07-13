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

    @Query(value="select * from users where UPPER(username) like UPPER('%'||:username||'%')", nativeQuery = true)
    public Page<Users> searchUsersByUsernamePaged(@Param("username") String username, Pageable pageable);
}
