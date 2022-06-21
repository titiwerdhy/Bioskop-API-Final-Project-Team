package com.teama.bioskop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teama.bioskop.Models.Seats;

public interface SeatsRepository extends JpaRepository<Seats, Integer> {
}
