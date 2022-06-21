package com.teama.bioskop.Service;

import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Repositories.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedule    (){
        return this.scheduleRepository.findAll();
    }

}