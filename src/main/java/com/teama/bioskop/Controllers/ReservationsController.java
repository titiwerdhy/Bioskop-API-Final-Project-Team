package com.teama.bioskop.Controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Reservations;
import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Models.Users;
import com.teama.bioskop.Services.ReservationsService;
import com.teama.bioskop.Services.ScheduleService;
import com.teama.bioskop.Services.UsersService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ReservationsController {
    private final ReservationsService reservationsService;
    private final UsersService usersService;
    private final ScheduleService scheduleService;

    /***
     * Get All data of reservations entity
     * @param model
     * @return
     * @throws DataNotFoundException
     */
    @GetMapping("/crud/reservations")
    public String getAll(Model model) throws DataNotFoundException{
        model.addAttribute("newReservation", new Reservations());
        try {
            List<Reservations> reservList = reservationsService.getAllReservations();
            Collections.reverse(reservList);
            List<Users> userList = usersService.getAllUsers();
            List<Schedule> scheduleList = scheduleService.getAllSchedule();
            model.addAttribute("error", false);
            model.addAttribute("message", null);
            model.addAttribute("reservations", reservList);
            model.addAttribute("newReserv", new Reservations());
            model.addAttribute("users", userList);
            model.addAttribute("loop", "reservation");
            model.addAttribute("schedules", scheduleList);
            return "reservations-crud";
        }catch(DataNotFoundException e) {
            model.addAttribute("error", false);
            model.addAttribute("message", e.getMessage());
            model.addAttribute("reservations", null);
            return "reservations-crud";
        }
    }

    /***
     * Create new data for reservations entity
     * @param model
     * @param reservations
     * @return
     * @throws DataNotFoundException
     */
    @PostMapping("/create/reservation")
    public String createNew(Model model, @ModelAttribute Reservations reservations) throws DataNotFoundException{
        try {
            reservationsService.createReservation(reservations);
            model.addAttribute("error", false);
            model.addAttribute("message", "Success created new reservation!");
            return "redirect:/crud/reservations";
        } catch (DataNotFoundException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", e.getMessage());
            return "redirect:/crud/reservations";
        }
    }

    /***
     * Update reservations entity by id
     * @param model
     * @param id
     * @param reservations
     * @return
     * @throws DataNotFoundException
     */
    @PutMapping("/update/reservation/{id}")
    public String updateById(Model model, @PathVariable("id") Integer id, @ModelAttribute Reservations reservations) throws DataNotFoundException{
        try {
            reservations.setReservationId(id);
            reservationsService.updateReservation(reservations);
            model.addAttribute("error", false);
            model.addAttribute("message", "Successfully updated reservation with id "+id+"!");
            return "redirect:/crud/reservations";
        } catch (DataNotFoundException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", e.getMessage());
            return "redirect:/crud/reservations";
        }
    }

    /***
     * Delete reservations entity by id
     * @param model
     * @param id
     * @param reservations
     * @return
     */
    @DeleteMapping("/delete/reservation/{id}")
    public String deleteById(Model model, @PathVariable("id") Integer id, @ModelAttribute Reservations reservations){
        try {
            reservations.setReservationId(id);
            reservationsService.deleteReservation(reservations);
            model.addAttribute("error", false);
            model.addAttribute("message", "Successfully deleted reservation with id "+id+"!");
            return "redirect:/crud/reservations";
        } catch (DataNotFoundException e) {
            model.addAttribute("error", true);
            model.addAttribute("message", e.getMessage());
            return "redirect:/crud/reservations";
        }
    }
}
