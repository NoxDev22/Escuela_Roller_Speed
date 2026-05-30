package com.school.roller_speed.models;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "Class_Groups")

@Schema(
    description = "Entidad que representa los grupos o clases de la escuela Roller Speed"
)

public class ClassGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
        description = "ID único del grupo",
        example = "1",
        accessMode = Schema.AccessMode.READ_ONLY
    )

    @Column(name = "group_id")
    private Integer groupId;

    @Schema(
        description = "Nombre del grupo",
        example = "Speed Demons A, Urban Rollers A, Urban Demons A"
    )

    @Column(name = "group_name")
    private String groupName;

    @Schema(
        description = "Nivel del grupo",
        example = "PRO, BEGINNER, MASTER"
    )

    @Column(name = "group_level")
    private String groupLevel;

    @Schema(
        description = "Docente asignado al grupo"
    )

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @Schema(
        description = "Horario asignado al grupo"
    )

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @JsonIgnore

    @Schema(
        hidden = true
    )

    @OneToMany(mappedBy = "group")
    private List<Student> students;

    // GETTERS Y SETTERS

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupLevel() {
        return groupLevel;
    }

    public void setGroupLevel(String groupLevel) {
        this.groupLevel = groupLevel;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}