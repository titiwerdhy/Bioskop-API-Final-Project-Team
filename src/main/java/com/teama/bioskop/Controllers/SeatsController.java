package com.teama.bioskop.Controllers;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Seats;
import com.teama.bioskop.Services.SeatsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
public class SeatsController {

    private final SeatsService seatsService;

    /***
     * Crud Seats
     * @param model
     * @return
     */
    @GetMapping("/crud/seats")
    public String getAll(Model model){
        List<Seats> seatsList = this.seatsService.getAllSeats();
        Collections.reverse(seatsList);
        model.addAttribute("seats", seatsList);
        model.addAttribute("newSeats", new Seats());
        model.addAttribute("updateSeats", new Seats());
        return "seats-crud";
    }

    /***
     * Create Seats
     * @param model
     * @param seats
     * @return
     */
    @PostMapping("/create/seat")
    public String createNew(Model model, @ModelAttribute Seats seats){
        seatsService.insertNewSeats(seats);
        return "redirect:/crud/seats";
    }

    /***
     * Update Seats
     * @param model
     * @param id
     * @param seats
     * @return
     */
    @PutMapping("/update/seat/{id}")
    public String updateById(Model model, @PathVariable("id") Integer id, @ModelAttribute Seats seats) {
        seats.setSeatId(id);
        seatsService.UpdateSeats(seats);
        return "redirect:/crud/seats";
    }

    /***
     * Delete Seats
     * @param model
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @DeleteMapping("/delete/seat/{id}")
    public String deleteById(Model model, @PathVariable("id") Integer id) throws DataNotFoundException {
        seatsService.deleteSeatById(id);
        return "redirect:/crud/seats";
    }




}
