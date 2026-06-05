package com.school.roller_speed.controllers.api;

import com.school.roller_speed.models.ClassGroup;
import com.school.roller_speed.services.GroupService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")

@Tag(
    name = "Grupos",
    description = "CRUD de grupos de la Escuela Roller Speed"
)

public class GroupApiController {

    private final GroupService groupService;

    public GroupApiController(GroupService groupService) {
        this.groupService = groupService;
    }

    // LISTAR TODOS

    @Operation(summary = "Listar todos los grupos")
    @GetMapping
    public List<ClassGroup> listarGrupos() {
        return groupService.getAllGroups();
    }

    // BUSCAR POR ID

    @Operation(summary = "Buscar grupo por ID")
    @GetMapping("/{id}")
    public ClassGroup buscarPorId(@PathVariable Integer id) {
        return groupService.getGroupById(id);
    }

    // CREAR

    @Operation(summary = "Crear grupo")
    @PostMapping
    public void crearGrupo(@RequestBody ClassGroup group) {
        groupService.saveGroup(group);
    }

    // ACTUALIZAR

    @Operation(summary = "Actualizar grupo")
    @PutMapping
    public void actualizarGrupo(@RequestBody ClassGroup group) {
        groupService.updateGroup(group);
    }

    // ELIMINAR

    @Operation(summary = "Eliminar grupo")
    @DeleteMapping("/{id}")
    public void eliminarGrupo(@PathVariable Integer id) {
        groupService.deleteGroup(id);
    }
}