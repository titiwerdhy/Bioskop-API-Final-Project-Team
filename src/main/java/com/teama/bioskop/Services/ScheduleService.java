package com.teama.bioskop.Services;

import com.teama.bioskop.Helpers.ScheduleNotFoundException;
import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Repositories.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getAllSchedule(){
        return this.scheduleRepository.findAll();
    }

    public Schedule createSchedule (Schedule schedule){
        return this.scheduleRepository.save(schedule);
    }

    public Schedule getOneSchedule(Integer id) throws ScheduleNotFoundException {
        Optional<Schedule> optionalSchedule = this.scheduleRepository.findById(id);

        if(optionalSchedule.isEmpty()){
            throw new ScheduleNotFoundException("Schedule is Not Available");
        }

        return optionalSchedule.get();
    }

    public Schedule updateSchedule(Schedule schedule) throws ScheduleNotFoundException {
        this.getOneSchedule(schedule.getScheduleId());

        return this.scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Schedule schedule) throws ScheduleNotFoundException {
        Optional<Schedule> deletedSchedule = this.scheduleRepository.findById(schedule.getScheduleId());
        if(deletedSchedule.isEmpty()){
            throw new ScheduleNotFoundException("Schedule is Not Available");
        }

        this.scheduleRepository.delete(deletedSchedule.get());
    }
}