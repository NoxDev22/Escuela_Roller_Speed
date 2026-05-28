package com.school.roller_speed.controllers.api;

import com.school.roller_speed.models.Student;
import com.school.roller_speed.services.StudentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")

@Tag(
    name = "Estudiantes",
    description = "CRUD de estudiantes de la Escuela Roller Speed"
)

public class StudentApiController {

    @Autowired
    private StudentService studentService;

    // LISTAR TODOS

    @Operation(summary = "Listar todos los estudiantes")

    @GetMapping
    public List<Student> listarEstudiantes() {

        return studentService.listarEstudiantes();

    }

    // BUSCAR POR ID

    @Operation(summary = "Buscar estudiante por ID")

    @GetMapping("/{id}")
    public Student buscarPorId(
            @PathVariable Long id
    ) {

        return studentService.buscarPorId(id);

    }

    // CREAR ESTUDIANTE

    @Operation(summary = "Crear nuevo estudiante")

    @PostMapping
    public Student crearEstudiante(
            @Valid @RequestBody Student student
    ) {

        return studentService.guardarEstudiante(student);

    }

    // ACTUALIZAR ESTUDIANTE

    @Operation(summary = "Actualizar estudiante")

    @PutMapping("/{id}")
    public Student actualizarEstudiante(
            @PathVariable Long id,

            @Valid @RequestBody Student student
    ) {

        student.setStudentId(id);

        return studentService.guardarEstudiante(student);

    }

    // ELIMINAR ESTUDIANTE

    @Operation(summary = "Eliminar estudiante")

    @DeleteMapping("/{id}")
    public String eliminarEstudiante(
            @PathVariable Long id
    ) {

        studentService.eliminarEstudiante(id);

        return "Estudiante eliminado correctamente";

    }

}