package com.teama.bioskop.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teama.bioskop.Models.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
