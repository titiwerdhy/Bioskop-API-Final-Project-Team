package com.teama.bioskop.Services;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Reservations;
import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Models.Users;
import com.teama.bioskop.Repositories.ReservationsRepository;
import com.teama.bioskop.Repositories.ScheduleRepository;
import com.teama.bioskop.Repositories.UsersRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReservationsService {
    private final ReservationsRepository reservationsRepository;
    private final UsersRepository usersRepository;
    private final ScheduleRepository scheduleRepository;

    public List<Reservations> getAllReservations() throws DataNotFoundException{
        List<Reservations> reservationsList = this.reservationsRepository.findAll();
        if (reservationsList.isEmpty()) {
            throw new DataNotFoundException("No reservations exist!");
        }
        return this.reservationsRepository.findAll();
    }

    public Reservations getReservationById(Integer Id) throws DataNotFoundException {
        Optional<Reservations> optionalReservations = this.reservationsRepository.findById(Id);
        if (optionalReservations.isEmpty()){
            throw new DataNotFoundException("Reservation with id :"+Id+ " not exist ");
        }
        return optionalReservations.get();
    }

    public Reservations createReservation (Reservations reservations) throws DataNotFoundException{
        Optional<Users> optionalUser = this.usersRepository.findById(reservations.getUsers().getUserId());
        Optional<Schedule> optionalSchedule = this.scheduleRepository.findById(reservations.getSchedule().getScheduleId());
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("User with id "+ reservations.getUsers().getUserId() +" is not exist");
        }else if(optionalSchedule.isEmpty()){
            throw new DataNotFoundException("Schedule with id "+ reservations.getSchedule().getScheduleId() +" is not exist");
        }
        reservations.setUsers(optionalUser.get());
        reservations.setSchedule(optionalSchedule.get());
        return this.reservationsRepository.save(reservations);
    }
    public Reservations updateReservation (Reservations reservations) throws DataNotFoundException {
        Optional<Reservations>  optionalReservations = this.reservationsRepository.findById(reservations.getReservationId());
        if (optionalReservations.isEmpty()){
            throw new DataNotFoundException("Reservation with id :"+ reservations.getReservationId()+ " not exist ");
        }
        return this.reservationsRepository.save(reservations);
    }

    public void deleteReservation (Reservations reservations) throws DataNotFoundException {
        Optional<Reservations> optionalReservations = this.reservationsRepository.findById(reservations.getReservationId());
        if (optionalReservations.isEmpty()){
            throw new DataNotFoundException("Reservation with id :"+ reservations.getReservationId()+ " not exist ");
        }
        this.reservationsRepository.delete(optionalReservations.get());
    }

}