package com.teama.bioskop.Repositories;

import com.teama.bioskop.Models.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {

}
