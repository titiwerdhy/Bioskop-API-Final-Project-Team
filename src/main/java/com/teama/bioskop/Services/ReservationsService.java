package com.teama.bioskop.Services;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Reservations;
import com.teama.bioskop.Repositories.ReservationsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@AllArgsConstructor
public class ReservationsService {
    private final ReservationsRepository reservationsRepository;

    public List<Reservations> getAllReservations(){
        return this.reservationsRepository.findAll();
    }

    public Reservations getReservationById(Integer Id) throws DataNotFoundException {
        Optional<Reservations> optionalReservations = this.reservationsRepository.findById(Id);
        if (optionalReservations.isEmpty()){
            throw new DataNotFoundException("Reservation with id :"+Id+ " not exist ");
        }
        return optionalReservations.get();
    }

    public Reservations createReservation (Reservations reservations){
        return this.reservationsRepository.save(reservations);
    }

    public Reservations updateReservation (Reservations reservations) throws DataNotFoundException {
        Optional<Reservations>  optionalReservations = this.reservationsRepository.findById(reservations.getReservationId());
        if (optionalReservations.isEmpty()){
            throw new DataNotFoundException("Reservation with id :"+ reservations.getReservationId()+ " not exist ");
        }
        return this.reservationsRepository.save(optionalReservations.get());
    }

    public void deleteReservation (Reservations reservations) throws DataNotFoundException {
        Optional<Reservations> optionalReservations = this.reservationsRepository.findById(reservations.getReservationId());
        if (optionalReservations.isEmpty()){
            throw new DataNotFoundException("Reservation with id :"+ reservations.getReservationId()+ " not exist ");
        }
        this.reservationsRepository.delete(optionalReservations.get());
    }

}
