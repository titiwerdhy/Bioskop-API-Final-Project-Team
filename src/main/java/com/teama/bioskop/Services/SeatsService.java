package com.teama.bioskop.Services;

import com.teama.bioskop.Models.Seats;
import com.teama.bioskop.Repositories.SeatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SeatsService {
    private SeatsRepository seatsRepository;

    public List<Seats> getAllSeats(){
        return this.seatsRepository.findAll();
    }

    public Seats insertNewSeats(Seats seats) {
        return this.seatsRepository.save(seats);
    }
    public Seats UpdateSeats ( Seats seats ){
        return this.seatsRepository.save(seats);
    }

    public void deleteSeat(Integer id) {
        Seats deleteSeat = seatsRepository.getReferenceById(id);
        this.seatsRepository.delete(deleteSeat);
    }

    public Optional<Seats> getSeatById(Integer id){
        return this.seatsRepository.findById(id);
    }

    public Seats getSeatsById(Integer seath) {
        Seats getSeath = seatsRepository.getReferenceById(seath);
        return this.seatsRepository.save(getSeath);
    }
    public List<Seats> getSeatsAvailable(Boolean isAvailable){

        List<Seats> seatsAvailable = this.seatsRepository.getSeatsAvailable(isAvailable);
        return seatsAvailable;
    }
}
