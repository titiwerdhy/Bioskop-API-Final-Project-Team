package com.teama.bioskop.Services;

import com.teama.bioskop.Models.Seats;
import com.teama.bioskop.Repositories.SeatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SeatsService {
    private SeatsRepository seatsRepository;

    public List<Seats> getAllSeats(){
      return this.seatsRepository.findAll();
    }
}
