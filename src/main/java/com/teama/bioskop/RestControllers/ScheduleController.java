package com.teama.bioskop.RestControllers;

import com.teama.bioskop.DTOs.ScheduleRequestDTO;
import com.teama.bioskop.DTOs.ScheduleResponseDTO;
import com.teama.bioskop.Handlers.ResponseHandlers;
import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Films;
import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Services.ScheduleService;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    private static final Logger logger = LogManager.getLogger(ScheduleController.class);


    /***
     * Get all data from Schedules table
     * @return ResponseEntity fully formed
     */
    @GetMapping("/schedules")
    public ResponseEntity<?> getAll() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK 1");

        try{

            List<Schedule> schedules = scheduleService.getAllSchedule();
            logger.info("------------------------------------");
            logger.info("GET ALL SCHEDULE DATA " + schedules);
            logger.info("-------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse("", HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), schedules);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);

        } catch (Exception e){

            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse("", HttpStatus.BAD_REQUEST, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        }
    }

    /***
     * Get one data from Schedules table with Path Variable
     * @param id from Path Variable
     * @return ResponseEntity fully formed
     * @throws DataNotFoundException if a data is not found
     */

    @GetMapping("/schedule/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable Integer id) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK 1");

        try {

            Schedule schedule = this.scheduleService.getOneSchedule(id);
            ScheduleResponseDTO responseDTO = schedule.convertToResponse();

            logger.info("------------------------------------");
            logger.info("GET ALL SCHEDULE DATA " + schedule);
            logger.info("-------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse("", HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), responseDTO);
            return ResponseEntity.ok().headers(headers).body(body);

        } catch (DataNotFoundException e){

            logger.error("------------------------------------");
            logger.error("DATA WITH ID "+ id + "IS NOT FOUND");
            logger.error("------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        }
    }

    /***
     * Post one data into Schedules table
     * @throws Exception if bad request happened
     * @return ResponseEntity fully formed
     */

    @PostMapping("/schedule")
    public ResponseEntity<?> createSchedule(@RequestBody ScheduleRequestDTO scheduleRequestDTO){

        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK 1");

        try {

            Schedule newSchedule = scheduleRequestDTO.convertToEntity();

            Schedule schedule = this.scheduleService.createSchedule(newSchedule);
            ScheduleResponseDTO responseDTO = schedule.convertToResponse();
            logger.info("------------------------------------");
            logger.info("SUCCESS CREATE DATA " + schedule);
            logger.info("-------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse("", HttpStatus.CREATED, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), responseDTO);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        } catch (Exception e){

            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse(e.getMessage(), HttpStatus.BAD_REQUEST, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        }

    }

    /***
     * Put one data from Schedules table with Path Variable
     * @param id from Path Variable
     * @throws DataNotFoundException if a data is not found
     * @return ResponseEntity fully formed
     */

    @PutMapping("/schedule/{id}")
    public ResponseEntity<?> updateSchedule(@PathVariable Integer id, @RequestBody ScheduleRequestDTO scheduleRequestDTO) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK 1");

        try {

            Schedule schedule = scheduleRequestDTO.convertToEntity();
            schedule.setScheduleId(id);

            Schedule updateSchedule = this.scheduleService.updateSchedule(schedule);
            ScheduleResponseDTO responseDTO = updateSchedule.convertToResponse();

            logger.info("------------------------------------");
            logger.info("SUCCESS UPDATE DATA " + schedule);
            logger.info("-------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse("", HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), responseDTO);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);

        } catch (DataNotFoundException e){

            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        }

    }

    /***
     * Get one data from Schedules table with Path Variable
     * @param id from Path Variable
     * @return ResponseEntity fully formed
     * @throws DataNotFoundException if a data is not found
     */

    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<?> deleteSchedule(@PathVariable Integer id) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK 1");

        try {

            Schedule schedule = new Schedule();
            schedule.setScheduleId(id);

            this.scheduleService.deleteSchedule(schedule);
            logger.info("------------------------------------");
            logger.info("SUCCESS DELETE DATA WITH ID " + id);
            logger.info("-------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse("", HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), "Data is deleted");
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        } catch (DataNotFoundException e){

            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), "Data is deleted");
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        }
    }

    /***
     * Get one data from Schedules with requestBody
     * @return ResponseEntity fully formed
     * @throws DataNotFoundException if data is not found
     */

    @PostMapping("/schedule/filmname")
    public ResponseEntity<?> findScheduleByFilmName(@RequestBody Films films) throws DataNotFoundException{

        HttpHeaders headers = new HttpHeaders();
        headers.set("APP-NAME", "BIOSKOP API KELOMPOK 1");

        try {
            List<Schedule> schedulesByFilmNameList = this.scheduleService.getSchedulesByFilmName(films.getFilmName());

            logger.info("------------------------------------");
            logger.info("SUCCESS FIND THE DATA " + schedulesByFilmNameList);
            logger.info("-------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse("", HttpStatus.OK, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), schedulesByFilmNameList);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);

        } catch (DataNotFoundException e){
            logger.error("------------------------------------");
            logger.error(e.getMessage());
            logger.error("------------------------------------");

            ResponseEntity<?> body = ResponseHandlers.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, headers, ZonedDateTime.now(ZoneId.of("Asia/Tokyo")), null);
            return ResponseEntity.status(body.getStatusCode()).headers(headers).body(body);
        }

    }
}