package com.school.roller_speed.models;

import jakarta.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "Schedules")

@Schema(
    description = "Entidad que representa los horarios disponibles de la escuela Roller Speed"
)

public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
        description = "ID único del horario",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )

    @Column(name = "schedule_id")
    private Integer scheduleId;

    @Schema(
        description = "Horario asignado a una clase o grupo",
        example = "Lunes y Miércoles 4:00 PM - 6:00 PM"
    )

    @Column(name = "schedule_class")
    private String scheduleClass;

    // GETTERS Y SETTERS

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getScheduleClass() {
        return scheduleClass;
    }

    public void setScheduleClass(String scheduleClass) {
        this.scheduleClass = scheduleClass;
    }
}