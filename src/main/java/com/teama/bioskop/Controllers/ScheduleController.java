package com.teama.bioskop.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.teama.bioskop.Helpers.DataNotFoundException;
import com.teama.bioskop.Models.Schedule;
import com.teama.bioskop.Services.ScheduleService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ScheduleController {
    @Autowired
    private final ScheduleService scheduleService;

    /***
     * CRUD Schedule
     * @param model
     * @return
     */
    @GetMapping("/crud/schedule")
    public String getAll(Model model){
        List<Schedule> scheduleList = this.scheduleService.getAllSchedule();
        model.addAttribute("schedule", scheduleList);
        model.addAttribute("newSchedule", new Schedule());
        model.addAttribute("updateSchedule", new Schedule());
        return "schedule-crud";
    }
    /***
     * Create new Schedule
     * @param model
     * @return
     */
    @PostMapping("/create/schedule")
    public String createNew(Model model, @ModelAttribute Schedule schedule){
        scheduleService.createSchedule(schedule);
        return "redirect:/crud/schedule";
    }
    /***
     * Update schedule
     * @param model
     * @param schedule
     * @return
     * @throws DataNotFoundException
     */
    @PutMapping("/update/schedule/{id}")
    public String updateById(Model model, @PathVariable("id") Integer id, @ModelAttribute Schedule schedule) throws DataNotFoundException{
        schedule.setScheduleId(id);
        scheduleService.updateScheduleById(schedule);
        return "redirect:/crud/schedule";
    }
    /***
     * Delete Schedule
     * @param model
     * @param id
     * @return
     * @throws DataNotFoundException
     */
    @DeleteMapping("/delete/schedule/{id}")
    public String deleteById(Model model, @PathVariable("id") Integer id) throws DataNotFoundException{
        Schedule schedule = scheduleService.getOneSchedule(id);
        scheduleService.deleteScheduleById(schedule);
        return "redirect:/crud/schedule";
    }
}
