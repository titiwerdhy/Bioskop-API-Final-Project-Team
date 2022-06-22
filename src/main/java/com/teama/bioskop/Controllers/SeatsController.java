package com.teama.bioskop.Controllers;

import com.teama.bioskop.Models.Seats;
import com.teama.bioskop.Services.SeatsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class SeatsController {
    private final SeatsService seatsService;

    @GetMapping("/seats")
    public List<Seats> getAll() {
        return this.seatsService.getAllSeats();
    }

    @GetMapping("/seats/{id}")
    public Optional<Seats> getById(@PathVariable("id") Integer id) {
        return this.seatsService.getSeatById(id);
    }

    @PostMapping("/seats")
    public Seats InsertSeats(@RequestBody Seats seats) {
        seatsService.insertNewSeats(seats);
        return seats;
    }

    @PutMapping("/seats")
    public Seats UpdateSeats(@RequestBody Seats seats) {
        seatsService.UpdateSeats(seats);
        return seats;
    }

    @DeleteMapping("/seats/{id}")
    public ResponseEntity<Optional<Seats>> deleteSeat(@PathVariable("id") Integer id) {
        Optional<Seats> deletedSeats = seatsService.getSeatById(id);
        seatsService.deleteSeat(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedSeats);
    }
}