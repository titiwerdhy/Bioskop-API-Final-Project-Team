package com.teama.bioskop.Repositories;

import com.teama.bioskop.Models.Reservations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {

    @Query("select r from Reservations r where lower(r.users.username) like lower(concat('%',:username,'%'))")
    public Page<Reservations> getReservationByUsername(@Param("username") String username, Pageable pageable);
}
