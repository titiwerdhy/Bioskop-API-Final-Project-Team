package com.teama.bioskop.Controllers;

import com.teama.bioskop.DTOs.SeatsRequestDTO;
import com.teama.bioskop.DTOs.SeatsResponseDTO;
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
    public ResponseEntity<List<Seats>> getAll() {
        List<Seats> allSeats = this.seatsService.getAllSeats();
        logger.info("Get all seat: " +allSeats);
        return ResponseEntity.status(HttpStatus.OK).body(allSeats);
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<SeatsResponseDTO> getById(@PathVariable("id") Integer id) throws DataNotFoundException {
        Seats seats = this.seatsService.getSeatById(id);
        SeatsResponseDTO seatsResponseDTO = seats.convertToResponse();
        logger.info("Get seat by id : " + seats);
        return ResponseEntity.status(HttpStatus.OK).body(seatsResponseDTO);
    }

    @PostMapping("/seats")
    public ResponseEntity<Seats> InsertSeats(@RequestBody SeatsRequestDTO seatsRequestDTO) {
        Seats createdSeat = seatsService.insertNewSeats(seatsRequestDTO.converToSeat());
        logger.info("Create new seat : " + createdSeat );
        return ResponseEntity.status(HttpStatus.OK).body(createdSeat);
    }

    @PutMapping("/seats")
    public ResponseEntity<Seats> UpdateSeats(@RequestBody SeatsRequestDTO seatsRequestDTO) {
        Seats updatedSeat = seatsService.UpdateSeats(seatsRequestDTO.converToSeat());
        logger.info("Update new seat : " + updatedSeat);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSeat);
    }

    @DeleteMapping("/seats/{id}")
    public ResponseEntity<Seats> deleteSeat(@PathVariable("id") Integer id) throws DataNotFoundException {
        Seats deletedSeat = seatsService.getSeatById(id);
        seatsService.deleteSeatById(id);
        logger.info("Delete seat : " + deletedSeat);
        return ResponseEntity.status(HttpStatus.OK).body(deletedSeat);
    }

    @PostMapping("/seats/available")
    public ResponseEntity<List<Seats>> findSeatsAvailable(@RequestBody Seats seats){
        List<Seats> seatsAvailable = this.seatsService.getSeatsAvailable(seats.getIsAvailable());
        logger.info("Create new seat available : " + seatsAvailable);
        return ResponseEntity.status(HttpStatus.OK).body(seatsAvailable);
    }
}