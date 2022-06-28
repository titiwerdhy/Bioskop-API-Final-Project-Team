package com.teama.bioskop.Controllers;

import com.teama.bioskop.DTOs.SeatsRequestDTO;
import com.teama.bioskop.DTOs.SeatsResponseDTO;
import com.teama.bioskop.Handlers.ResponseHandler;
import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Seats;
import com.teama.bioskop.Services.SeatsService;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SeatsController {

    private static final Logger logger = LogManager.getLogger(SeatsController.class);
    private final SeatsService seatsService;

    @GetMapping("/seats")
    public ResponseEntity<Object> getAll() {
        try {
            List<Seats> allSeats = this.seatsService.getAllSeats();
            logger.info("-------------------------------");
            logger.info("GET ALL SEAT DATA: " + allSeats);
            logger.info("-------------------------------");
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, allSeats);
        } catch (Exception e) {
            logger.error("SEAT DATA DOESN'T HAVE ANY DATA!");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);

        }
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) throws DataNotFoundException {
        try {
            Seats seats = this.seatsService.getSeatById(id);
            SeatsResponseDTO seatsResponseDTO = seats.convertToResponse();
            logger.info("--------------------------");
            logger.info("GET SEAT BY ID : " + seats);
            logger.info("--------------------------");
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, seats);
        } catch (DataNotFoundException e) {
            logger.error("--------------------------");
            logger.error("GET SEAT BY ID " + id + " NOT FOUND");
            logger.error("--------------------------");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/seats")
    public ResponseEntity<Object> InsertSeats(@RequestBody SeatsRequestDTO seatsRequestDTO) {
        try {

            Seats newseats = seatsRequestDTO.converToSeat();
            Seats seats = this.seatsService.getSeatById(newseats);
            SeatsResponseDTO responseDTO = seats.convertToResponse();

            logger.info("--------------------------");
            logger.info("SEAT SUCCESSFULLY RECORDED");
            logger.info("--------------------------");

            return ResponseHandler.generateResponse("Seat Successfully Recorded", HttpStatus.OK, seats);
        } catch (Exception e) {

            Seats createdSeat = seatsService.insertNewSeats(seatsRequestDTO.converToSeat());
            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    @PutMapping("/seats")
    public ResponseEntity<Object> UpdateSeats(@PathVariable Integer id, @RequestBody SeatsRequestDTO seatsRequestDTO) throws DataNotFoundException {
        try {
            Seats seat = seatsRequestDTO.converToSeat();
            seat.setSeatId(id);
            Seats updatedSeat = this.seatsService.UpdateSeats(seat);

            logger.info("--------------------------");
            logger.info("FILM SUCCESSFULLY UPDATED" + updatedSeat);
            logger.info("--------------------------");

            return ResponseHandler.generateResponse("Seat Updated!", HttpStatus.OK, updatedSeat);
        } catch (Exception e) {

            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping("/seats/{id}")
    public ResponseEntity<Object> deleteSeat(@PathVariable Integer id) {
        Seats seat = new Seats();
        seat.setSeatId(id);

        this.seatsService.deleteSeatById(seat);

        logger.info("--------------------------");
        logger.info("SUCCESS DELETE BY ID " + id);
        logger.info("--------------------------");
        return ResponseHandler.generateResponse("Seat Deleted!", HttpStatus.OK, seat);
    }


    @PostMapping("/seats/available")
    public ResponseEntity<Object> findSeatsAvailable(@RequestBody Seats seats){
        try {
        List<Seats> seatsAvailable = this.seatsService.getSeatsAvailable(seats.getIsAvailable());
            logger.info("--------------------------");
            logger.info("GET DATA BY STUDIO NAME "+ seatsAvailable);
            logger.info("--------------------------");
            return ResponseHandler.generateResponse("Success Get All Data By Studio Name", HttpStatus.OK, seatsAvailable);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}