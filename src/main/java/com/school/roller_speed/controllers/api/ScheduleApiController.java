package com.school.roller_speed.controllers.api;

import com.school.roller_speed.models.Schedule;
import com.school.roller_speed.services.ScheduleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")

@Tag(
    name = "Horarios",
    description = "Consulta de horarios de la Escuela Roller Speed"
)

public class ScheduleApiController {

    private final ScheduleService scheduleService;

    public ScheduleApiController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // SOLO LISTAR

    @Operation(summary = "Listar horarios")
    @GetMapping
    public List<Schedule> listarHorarios() {
        return scheduleService.getAllSchedules();
    }
}