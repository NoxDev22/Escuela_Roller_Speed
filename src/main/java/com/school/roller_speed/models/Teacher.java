package com.school.roller_speed.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Entity
@Table(name = "Teachers")

@Access(AccessType.FIELD)

@Schema(
    description = "Entidad que representa los docentes de la Escuela Roller Speed"
)

public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
        accessMode = Schema.AccessMode.READ_ONLY,
        description = "ID único del docente",
        example = "2"
    )

    @Column(name = "teacher_id")
    private Long teacherId;

    @NotBlank(message = "El primer nombre es obligatorio")

    @Size(max = 50)

    @Schema(
        description = "Primer nombre del docente",
        example = "Carlos"
    )

    @Column(name = "firstName")
    private String firstName;

    @Size(max = 50)

    @Schema(
        description = "Segundo nombre del docente",
        example = "Andrés"
    )

    @Column(name = "secondName")
    private String secondName;

    @NotBlank(message = "El primer apellido es obligatorio")

    @Size(max = 50)

    @Schema(
        description = "Primer apellido del docente",
        example = "Gómez"
    )

    @Column(name = "lastName")
    private String lastName;

    @Size(max = 50)

    @Schema(
        description = "Segundo apellido del docente",
        example = "Ramírez"
    )

    @Column(name = "secondLastName")
    private String secondLastName;

    @NotBlank(message = "El número de documento es obligatorio")

    @Size(max = 20)

    @Schema(
        description = "Número de documento del docente",
        example = "1234567890"
    )

    @Column(name = "documentNumber")
    private String documentNumber;

    @NotNull(message = "La fecha de nacimiento es obligatoria")

    @Schema(
        description = "Fecha de nacimiento del docente",
        example = "1990-05-15"
    )

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Email(message = "Debe ingresar un correo válido")

    @Schema(
        description = "Correo electrónico del docente",
        example = "carlos@gmail.com"
    )

    @Column(name = "gmail")
    private String gmail;

    @NotBlank(message = "El número de teléfono es obligatorio")

    @Size(max = 15)

    @Schema(
        description = "Número telefónico del docente",
        example = "3001234567"
    )

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Schema(
        description = "Título académico del docente",
        example = "Licenciado en Educación Física"
    )

    @Column(name = "Academic_title")
    private String academicTitle;

    @Schema(
        description = "Dirección de residencia del docente",
        example = "Calle 10 #20-30"
    )

    @Column(name = "direction")
    private String direction;

    @Schema(
        description = "Usuario asociado al docente"
    )

    @OneToOne(cascade = {
    CascadeType.PERSIST,
    CascadeType.MERGE
    })
    @JoinColumn(name = "user_id")
    private SystemUser user;

    // GETTERS Y SETTERS

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(String academicTitle) {
        this.academicTitle = academicTitle;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }
}