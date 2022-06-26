package com.teama.bioskop.Controllers;
import com.teama.bioskop.DTOs.ScheduleRequestDTO;
import com.teama.bioskop.DTOs.ScheduleResponseDTO;
import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Models.Schedule;

import com.teama.bioskop.Services.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/schedules")
    public List<Schedule> getAll() {
        return this.scheduleService.getAllSchedule();

    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDTO> getScheduleById(@PathVariable Integer id) throws DataNotFoundException {
        Schedule schedule = this.scheduleService.getOneSchedule(id);

        ScheduleResponseDTO responseDTO = schedule.convertToResponse();

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponseDTO> createSchedule(@RequestBody ScheduleRequestDTO scheduleRequestDTO){
        Schedule newSchedule = scheduleRequestDTO.convertToEntity();

        Schedule schedule = this.scheduleService.createSchedule(newSchedule);
        ScheduleResponseDTO responseDTO = schedule.convertToResponse();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PutMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDTO> updateSchedule(@PathVariable Integer id, @RequestBody ScheduleRequestDTO scheduleRequestDTO) throws DataNotFoundException {
        Schedule schedule = scheduleRequestDTO.convertToEntity();

        schedule.setScheduleId(id);

        Schedule updateSchedule = this.scheduleService.updateSchedule(schedule);

        return ResponseEntity.ok(updateSchedule.convertToResponse());
    }

    @DeleteMapping("/schedule/{id}")
    public void deleteSchedule(@PathVariable Integer id) throws DataNotFoundException {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(id);

        this.scheduleService.deleteSchedule(schedule);
    }

    @PostMapping("/schedule/filmname")
    public List<Schedule> findScheduleByFilmName(@RequestBody Films films) throws DataNotFoundException{
        List<Schedule> schedulesByFilmNameList = this.scheduleService.getSchedulesByFilmName(films.getFilmName());

        return schedulesByFilmNameList;
    }
}