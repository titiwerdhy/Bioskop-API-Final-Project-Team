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

    public Optional<Seats> CreateSeats(Integer seats) { return this.seatsRepository.findById(seats);
    }

    public Seats insertNewSeats(Seats seats) {return this.seatsRepository.save(seats);
    }
    public Seats UpdateSeats (Integer seath){
        Seats UpdateSeats = seatsRepository.getReferenceById(seath);
        return this.seatsRepository.save(UpdateSeats);
    }


    public Seats deleteSeath(Integer seath) {
        Seats deleteSeath = seatsRepository.getReferenceById(seath);
        return this.seatsRepository.save(deleteSeath);
    }
}
