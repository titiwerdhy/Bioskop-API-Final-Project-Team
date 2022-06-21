package com.teama.bioskop.Controller;

import com.teama.bioskop.Models.Seats;
import com.teama.bioskop.Services.SeatsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SeatsController {
    private final SeatsService seatsService;

    @GetMapping("/seats")
    public List<Seats> getAll() {
        return this.seatsService.getAllSeats();

    }
}
