package com.teama.bioskop.Services;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Models.Seats;
import com.teama.bioskop.Repositories.ScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Schedule> getAllSchedulePaged(Double price, int pageNo, int pageSize, String sort, String order){
        Pageable pageable;
        if (sort == null) {
            if (price == null) {
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("updatedAt").descending());
            }else{
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("updated_at").descending());
            }
        }else{
            if (order.equals("ascending")) {
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sort).ascending());
            }else{
                pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(sort).descending());
            }
        }
        if (price == null) {
            return this.scheduleRepository.findAll(pageable);
        }else{
            return this.scheduleRepository.getScheduleByPrice(price, pageable);
        }
    }

    public Schedule createSchedule (Schedule schedule){
        return this.scheduleRepository.save(schedule);
    }

    public Schedule getOneSchedule(Integer id) throws DataNotFoundException {
        Optional<Schedule> optionalSchedule = this.scheduleRepository.findById(id);

        if(optionalSchedule.isEmpty()){
            throw new DataNotFoundException("Schedule is Not Available");
        }

        return optionalSchedule.get();
    }

    public Schedule updateScheduleById(Schedule schedule) throws DataNotFoundException {
        this.getOneSchedule(schedule.getScheduleId());

        return this.scheduleRepository.save(schedule);
    }

    public void deleteScheduleById(Schedule schedule) throws DataNotFoundException {
        Optional<Schedule> deletedSchedule = this.scheduleRepository.findById(schedule.getScheduleId());
        if(deletedSchedule.isEmpty()){
            throw new DataNotFoundException("Schedule is Not Available");
        }

        this.scheduleRepository.delete(deletedSchedule.get());
    }

    public List<Schedule> getSchedulesByFilmName(String filmName) throws DataNotFoundException{
        List<Schedule> schedulesByFilmName = this.scheduleRepository.getScheduleByFilmName(filmName);
        if(schedulesByFilmName.isEmpty()){
            throw new DataNotFoundException("Schedule with film name "+ filmName +" is Not Available");
        }

        return schedulesByFilmName;
    }

}