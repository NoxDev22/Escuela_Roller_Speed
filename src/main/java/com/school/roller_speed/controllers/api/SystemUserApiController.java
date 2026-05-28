package com.school.roller_speed.controllers.api;

import com.school.roller_speed.models.SystemUser;
import com.school.roller_speed.services.SystemUserService;

import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")

@Tag(
    name = "Usuarios",
    description = "CRUD de usuarios del sistema Roller Speed"
)

public class SystemUserApiController {

    @Autowired
    private SystemUserService userService;

    // LISTAR TODOS

    @Operation(
        summary = "Listar todos los usuarios",
        description = "Obtiene una lista completa de usuarios registrados en el sistema"
    )

    @ApiResponses(value = {

        @ApiResponse(
            responseCode = "200",
            description = "Lista de usuarios obtenida correctamente"
        )

    })

    @GetMapping
    public List<SystemUser> listarUsuarios() {

        return userService.listarUsuarios();

    }

    // BUSCAR POR ID

    @Operation(
        summary = "Buscar usuario por ID",
        description = "Obtiene un usuario específico mediante su ID"
    )

    @ApiResponses(value = {

        @ApiResponse(
            responseCode = "200",
            description = "Usuario encontrado correctamente"
        ),

        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado"
        )

    })

    @GetMapping("/{id}")
    public SystemUser buscarPorId(
            @PathVariable Long id
    ) {

        return userService.buscarPorId(id);

    }

    // CREAR USUARIO

    @Operation(
        summary = "Crear nuevo usuario",
        description = "Registra un nuevo usuario en el sistema Roller Speed"
    )

    @ApiResponses(value = {

        @ApiResponse(
            responseCode = "200",
            description = "Usuario creado correctamente"
        ),

        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos enviados"
        )

    })

    @PostMapping
    public SystemUser crearUsuario(
            @Valid @RequestBody SystemUser usuario
    ) {

        userService.guardarUsuario(usuario);

        return usuario;

    }

    // ACTUALIZAR USUARIO

    @Operation(
        summary = "Actualizar usuario",
        description = "Actualiza la información de un usuario existente"
    )

    @ApiResponses(value = {

        @ApiResponse(
            responseCode = "200",
            description = "Usuario actualizado correctamente"
        ),

        @ApiResponse(
            responseCode = "400",
            description = "Datos inválidos"
        ),

        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado"
        )

    })

    @PutMapping("/{id}")
    public SystemUser actualizarUsuario(
            @PathVariable Long id,

            @Valid @RequestBody SystemUser usuario
    ) {

        usuario.setUserId(id);

        userService.guardarUsuario(usuario);

        return usuario;

    }

    // ELIMINAR USUARIO

    @Operation(
        summary = "Eliminar usuario",
        description = "Elimina un usuario del sistema mediante su ID"
    )

    @ApiResponses(value = {

        @ApiResponse(
            responseCode = "200",
            description = "Usuario eliminado correctamente"
        ),

        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado"
        )

    })

    @DeleteMapping("/{id}")
    public String eliminarUsuario(
            @PathVariable Long id
    ) {

        userService.eliminarUsuario(id);

        return "Usuario eliminado correctamente";

    }

}