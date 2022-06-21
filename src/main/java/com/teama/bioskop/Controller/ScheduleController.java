package com.teama.bioskop.Controller;
import com.teama.bioskop.Models.Schedule;

import com.teama.bioskop.Services.ScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/schedules")
    public List<Schedule> getAll() {
        return this.scheduleService.getAllSchedule();

    }
}
