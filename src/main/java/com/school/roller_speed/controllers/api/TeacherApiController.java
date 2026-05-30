package com.school.roller_speed.controllers.api;

import com.school.roller_speed.models.Teacher;
import com.school.roller_speed.services.TeacherService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docentes")

@Tag(
    name = "Docentes",
    description = "CRUD de docentes de la Escuela Roller Speed"
)

public class TeacherApiController {

    @Autowired
    private TeacherService teacherService;

    // LISTAR TODOS

    @Operation(summary = "Listar todos los docentes")

    @GetMapping
    public List<Teacher> listarDocentes() {
        return teacherService.getAllTeachers();
    }

    // BUSCAR POR ID

    @Operation(summary = "Buscar docente por ID")
    @GetMapping("/{id}")
    public Teacher buscarPorId(
            @PathVariable Long id
    ) {
        return teacherService.getTeacherById(id);
    }

    // CREAR DOCENTE
    @Operation(summary = "Crear nuevo docente")
    @PostMapping
    public Teacher crearDocente(
            @Valid @RequestBody Teacher teacher
    ) {
        return teacherService.saveTeacher(teacher);
    }

    // ACTUALIZAR DOCENTE
    @Operation(summary = "Actualizar docente")
    @PutMapping("/{id}")
    public Teacher actualizarDocente(
            @PathVariable Long id,
            @Valid @RequestBody Teacher teacher
    ) {
        teacher.setTeacherId(id);
        return teacherService.saveTeacher(teacher);

    }

    // ELIMINAR DOCENTE
    @Operation(summary = "Eliminar docente")
    @DeleteMapping("/{id}")
    public String eliminarDocente(
            @PathVariable Long id
    ) {
        teacherService.deleteTeacher(id);
        return "Docente eliminado correctamente";

    }

}