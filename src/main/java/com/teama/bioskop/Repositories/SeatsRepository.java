package com.teama.bioskop.Repositories;

import com.teama.bioskop.Models.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, Integer> {
    Optional<Seats> findByseath(Integer seats);
}
