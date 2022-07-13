package com.teama.bioskop.Repositories;

import com.teama.bioskop.Models.Schedule;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query("select s from Schedule s where s.films.filmName =?1")
    public List<Schedule> getScheduleByFilmName(String filmName);
    @Query(value = "select * from schedule where harga_tiket =:price", nativeQuery = true)
    public Page<Schedule> getScheduleByPrice(@Param("price") double price, Pageable pageable);
}
