package com.teama.bioskop.Repositories;

import com.teama.bioskop.Models.Seats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<Seats, Integer> {

    @Query("select s from Seats s where isAvailable = ?1")
    public List<Seats> getSeatsAvailable(Boolean isAvailable);

}
