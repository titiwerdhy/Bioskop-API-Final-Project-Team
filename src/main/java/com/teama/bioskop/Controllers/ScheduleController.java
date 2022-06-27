package com.teama.bioskop.Controllers;

import com.teama.bioskop.DTOs.ScheduleRequestDTO;
import com.teama.bioskop.DTOs.ScheduleResponseDTO;
import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Services.ScheduleService;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    private static final Logger logger = LogManager.getLogger(ScheduleController.class);


    @GetMapping("/schedules")
    public List<Schedule> getAll() {
        List<Schedule> schedules = scheduleService.getAllSchedule();
        logger.info("------------------------------------");
        logger.info("GET ALL SCHEDULE DATA " + schedules);
        logger.info("-------------------------------------");
        return this.scheduleService.getAllSchedule();

    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDTO> getScheduleById(@PathVariable Integer id) throws DataNotFoundException {

        try {

            Schedule schedule = this.scheduleService.getOneSchedule(id);
            ScheduleResponseDTO responseDTO = schedule.convertToResponse();

            logger.info("------------------------------------");
            logger.info("GET ALL SCHEDULE DATA " + schedule);
            logger.info("-------------------------------------");

            return ResponseEntity.ok(responseDTO);

        } catch (DataNotFoundException e){

            logger.error("------------------------------------");
            logger.error("DATA WITH ID "+ id + "IS NOT FOUND");
            logger.error("------------------------------------");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

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