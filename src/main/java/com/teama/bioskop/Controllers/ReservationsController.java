package com.teama.bioskop.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teama.bioskop.DTOs.ReservationsRequestDTO;
import com.teama.bioskop.DTOs.ReservationsResponseDTO;
import com.teama.bioskop.Handlers.ResponseHandler;
import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Reservations;
import com.teama.bioskop.Services.ReservationsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ReservationsController {
    private final ReservationsService reservationsService;
    private final String line = "==============================";
    private static final Logger logger = LogManager.getLogger(Reservations.class);

    /***
     * Get All Reservations data
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/reservations")
    public ResponseEntity<Object> getAll() throws DataNotFoundException{
        try {
            List<Reservations> reservationsList = this.reservationsService.getAllReservations();
            List<ReservationsResponseDTO> reservationsResponseDTOs = new ArrayList<ReservationsResponseDTO>();
            for(Reservations reservation : reservationsList){
                reservationsResponseDTOs.add(reservation.convertToResponse());
            }
            logger.info(line + "Start Get All Reservation" + line);
            logger.info("Get All Reservations : " + reservationsResponseDTOs);
            logger.info(line + "End Get All Reservation" + line);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, reservationsResponseDTOs);
        } catch (DataNotFoundException e) {
            logger.error(line + "Start Get All Reservation" + line);
            logger.error("Reservation data is not exist!");
            logger.error(line + "End Get All Reservation" + line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }
    
    /***
     * Get Reservation data by ID
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/reservation/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) throws DataNotFoundException{
        try {
            Reservations reservation = this.reservationsService.getReservationById(id);
            ReservationsResponseDTO reservationsResponseDTO = reservation.convertToResponse();
            logger.info(line + "Start Get Reservation By Id" + line);
            logger.info("Get Reservations By Id : " + reservationsResponseDTO);
            logger.info(line + "End Get Reservation By Id" + line);
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, reservationsResponseDTO);
        } catch (DataNotFoundException e) {
            logger.error(line + "Start Get Reservation By Id" + line);
            logger.error("Reservation data with id " + id + " is not exist");
            logger.error(line + "End Get Reservation By Id" + line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
    }

    /***
     * Create new Reservation data
     * @param reservationsRequestDTO
     * @return
     * @throws DataNotFoundException
     */
    @PostMapping("/reservation")
    public ResponseEntity<Object> createNew(@RequestBody ReservationsRequestDTO reservationsRequestDTO) throws DataNotFoundException{
        Reservations newReservation = reservationsRequestDTO.convertToReservations();
        try {
            Reservations createdReservations = this.reservationsService.createReservation(newReservation);
            ReservationsResponseDTO reservationsResponseDTO = createdReservations.convertToResponse();
            logger.info(line + "Start Create New Reservation" + line);
            logger.info("Create New Reservation : " + reservationsResponseDTO);
            logger.info(line + "End Create New Reservation" + line);
            return ResponseHandler.generateResponse("New reservation data successfully created", HttpStatus.CREATED, reservationsResponseDTO);
        } catch (DataNotFoundException e) {
            ReservationsResponseDTO reservationsResponseDTO = newReservation.convertToResponse();
            logger.error(line + "Start Create New Reservation" + line);
            logger.error("Cannot create new Reservation : " + reservationsResponseDTO);
            logger.error(line + "End Create New Reservation" + line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, reservationsResponseDTO);
        }
    }

    /***
     * Update Reservation by ID
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @PutMapping("/reservation/{id}")
    public ResponseEntity<Object> updateById(@PathVariable Integer id, @RequestBody ReservationsRequestDTO reservationsRequestDTO) throws DataNotFoundException{
        Reservations reservation = reservationsRequestDTO.convertToReservations();
        reservation.setReservationId(id);
        try {
            Reservations updateReservation = this.reservationsService.updateReservation(reservation);
            ReservationsResponseDTO reservationsResponseDTO = updateReservation.convertToResponse();
            logger.info(line + "Start Update Reservation By Id" + line);
            logger.info("Update Reservation : " + reservationsResponseDTO);
            logger.info(line + "End Update Reservation By Id" + line);
            return ResponseHandler.generateResponse("Reservation with id "+ id +" successfully updated", HttpStatus.OK, reservationsResponseDTO);
        } catch (DataNotFoundException e) {
            logger.error(line + "Start Update Reservation By Id" + line);
            logger.error("Reservation data with id " + id + " is not exist");
            logger.error(line + "End Update Reservation By Id" + line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, reservation);
        }
    }

    /***
     * Delete Reservation By ID
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Integer id) throws DataNotFoundException{
        Reservations reservation = this.reservationsService.getReservationById(id);
        try {
            this.reservationsService.deleteReservation(reservation);
            ReservationsResponseDTO reservationsResponseDTO = reservation.convertToResponse();
            logger.info(line + "Start Delete Reservation By Id" + line);
            logger.info("Delete Reservation : " + reservationsResponseDTO);
            logger.info(line + "End Delete Reservation By Id" + line);
            return ResponseHandler.generateResponse("Successfully delete reservation with id: " + id, HttpStatus.OK, reservationsResponseDTO);
        } catch (DataNotFoundException e) {
            logger.error(line + "Start Delete Reservation By Id" + line);
            logger.error("Reservation data with id " + id + " is not exist");
            logger.error(line + "End Delete Reservation By Id" + line);
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.NOT_FOUND, reservation);
        }
    }
}
