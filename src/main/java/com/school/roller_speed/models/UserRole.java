package com.school.roller_speed.models;

import jakarta.persistence.*;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "User_Roles")

@Schema(
    description = "Entidad que representa los roles de usuario dentro del sistema Roller Speed"
)

public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
        description = """
        ID del rol del usuario.
        1 = Administrador
        2 = Docente
        3 = Estudiante
        """,
        example = "1"
    )

    @Column(name = "rol_id")
    private Long rolId;

    @Schema(
        description = "Nombre del rol asignado automáticamente",
        example = "Docente",
        accessMode = Schema.AccessMode.READ_ONLY
    )

    @Column(name = "rol_name")
    private String rolName;

    // GETTERS Y SETTERS

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getRolName() {
        return rolName;
    }

    public void setRolName(String rolName) {
        this.rolName = rolName;
    }
}