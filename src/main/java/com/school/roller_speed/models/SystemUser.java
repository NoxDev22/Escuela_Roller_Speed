package com.school.roller_speed.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "System_Users")

@Schema(
    description = "Entidad que representa los usuarios del sistema Roller Speed"
)

public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
        description = "ID único del usuario. Asignado automaticamente",
        example = "2",
        accessMode = Schema.AccessMode.READ_ONLY
    )

    @Column(name = "user_id")
    private Long userId;

    @NotBlank(
        message = "El nombre de usuario es obligatorio"
    )

    @Size(
        min = 1,
        max = 50,
        message = "El nombre de usuario debe tener entre 1 y 50 caracteres"
    )

    @Schema(
        description = "Nombre de usuario del sistema",
        example = "RPUD26_1"
    )

    @Column(name = "user_name")
    private String userName;

    @NotBlank(
        message = "La contraseña es obligatoria"
    )

    @Size(
        min = 4,
        max = 50,
        message = "La contraseña debe tener entre 4 y 50 caracteres"
    )

    @Schema(
        description = "Contraseña del usuario",
        example = "pass123"
    )

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    @Column(name = "user_password")
    private String userPassword;

    @Schema(
        description = "Estado de asignación del usuario",
        example = "false",
        accessMode = Schema.AccessMode.READ_ONLY
    )

    @Column(name = "user_assigned")
    private Boolean userAssigned = false;

    @NotNull(
        message = "El rol es obligatorio"
    )

    @Schema(
        description = "Rol asignado al usuario"
    )

    @ManyToOne
    @JoinColumn(name = "rol_id")
    private UserRole role;

    // GETTERS Y SETTERS

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Boolean getUserAssigned() {
        return userAssigned;
    }

    public void setUserAssigned(Boolean userAssigned) {
        this.userAssigned = userAssigned;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}