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

    /***
     * Get all data from Seayt table
     * @return List of Seat
     */


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

    /***
     * Get one Seat by Id
     * @param id of selected Seat
     * @return Selected Seat data
     * @throws DataNotFoundException
     */

    @GetMapping("/seats/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) throws DataNotFoundException {
        try {
            Seats seats = this.seatsService.getSeatById(id);
            SeatsResponseDTO SeatsResponseDTO = seats.convertToResponse();
            logger.info("--------------------------");
            logger.info("GET SEAT BY ID : " + seats);
            logger.info("--------------------------");
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, SeatsResponseDTO);
        } catch (DataNotFoundException e) {
            logger.error("--------------------------");
            logger.error("GET SEAT BY ID " + id + " NOT FOUND");
            logger.error("--------------------------");
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    /***
     * Insert new data to Seat table
     * @param seats new users data
     * @return new Seat
     */


    @PostMapping("/seats")
    public ResponseEntity<Object> InsertSeats(@RequestBody SeatsRequestDTO seatsRequestDTO) {
        try {

            Seats newseats = seatsRequestDTO.converToSeat();
            Seats seats = this.seatsService.getSeatById(newseats);
            SeatsResponseDTO responseDTO = seats.convertToResponse();

            logger.info("--------------------------");
            logger.info("SEAT SUCCESSFULLY RECORDED");
            logger.info("--------------------------");

            return ResponseHandler.generateResponse("Seat Successfully Recorded", HttpStatus.OK, responseDTO);
        } catch (Exception e) {

            Seats createdSeat = seatsService.insertNewSeats(seatsRequestDTO.converToSeat());
            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    /***
     * Update data to Seat table
     * @param seats Seat which going to be updated
     * @return updated Seat
     * @throws DataNotFoundException
     */
    @PutMapping("/seats/{id}")
    public ResponseEntity<Object> UpdateSeats(@PathVariable Integer id, @RequestBody SeatsRequestDTO seatsRequestDTO) throws DataNotFoundException {
        try {
            Seats seat = seatsRequestDTO.converToSeat();
            seat.setSeatId(id);
            Seats updatedSeat = this.seatsService.UpdateSeats(seat);

            logger.info("--------------------------");
            logger.info("SEAT SUCCESSFULLY UPDATED" + updatedSeat);
            logger.info("--------------------------");

            return ResponseHandler.generateResponse("Seat Updated!", HttpStatus.OK, updatedSeat);
        } catch (Exception e) {

            logger.error("--------------------------");
            logger.error(e.getMessage());
            logger.error("--------------------------");

            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    /***
     * Delete data to Seat table
     * @param id Seat which going to be deleted
     * @return response status
     * @throws DataNotFoundException
     */
    @DeleteMapping("/seats/{id}")
    public ResponseEntity<Object> deleteSeat(@PathVariable ("id") Integer id) throws DataNotFoundException {
        try {
            Seats deletedSeat = seatsService.getSeatById(id);

            seatsService.deleteSeatById(id);

            logger.info("--------------------------");
            logger.info("SUCCESS DELETE SEAT BY ID " + id);
            logger.info("--------------------------");
            return ResponseHandler.generateResponse("Successfully delete user!", HttpStatus.OK, deletedSeat);
        }catch (DataNotFoundException e) {
            logger.error("Cannot delete seat with ID " + id);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    /***
     * Insert new seat data available to Seat table
     * @param seats new seat data available
     * @return new Seat available
     */

    @PostMapping("/seats/available")
    public ResponseEntity<Object> findSeatsAvailable(@RequestBody Seats seats){
        try {
        List<Seats> seatsAvailable = this.seatsService.getSeatsAvailable(seats.getIsAvailable());
            logger.info("--------------------------");
            logger.info("GET SEAT DATA BY AVAILBILITY "+ seatsAvailable);
            logger.info("--------------------------");
            return ResponseHandler.generateResponse("Success Get Seat Available Data ", HttpStatus.OK, seatsAvailable);
        } catch (Exception e) {
            logger.error("------------------------------------");
            logger.error(e.getMessage());

            return null;
        }
    }
}