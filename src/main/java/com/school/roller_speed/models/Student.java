package com.school.roller_speed.models;

import jakarta.persistence.*;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "Students")

@Access(AccessType.FIELD)

@Schema(
    description = "Entidad que representa los estudiantes de la escuela Roller Speed"
)

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(
        description = "ID único del estudiante",
        example = "4",
        accessMode = Schema.AccessMode.READ_ONLY
    )

    @Column(name = "student_id")
    private Long studentId;

    @NotBlank(message = "El primer nombre es obligatorio")

    @Size(max = 30)

    @Schema(
        description = "Primer nombre del estudiante",
        example = "Juan"
    )

    @Column(name = "firstName")
    private String firstName;

    @Schema(
        description = "Segundo nombre del estudiante",
        example = "David"
    )

    @Column(name = "secondName")
    private String secondName;

    @NotBlank(message = "El apellido es obligatorio")

    @Size(max = 30)

    @Schema(
        description = "Primer apellido del estudiante",
        example = "Martinez"
    )

    @Column(name = "lastName")
    private String lastName;

    @NotBlank(message = "El segundo apellido es obligatorio")

    @Size(max = 30)

    @Schema(
        description = "Segundo apellido del estudiante",
        example = "Rios"
    )

    @Column(name = "secondLastName")
    private String secondLastName;

    @NotBlank(message = "El tipo de documento es obligatorio")

    @Schema(
        description = "Tipo de documento del estudiante",
        example = "CC"
    )

    @Column(name = "document_type")
    private String documentType;

    @NotBlank(message = "El número de documento es obligatorio")

    @Schema(
        description = "Número de documento del estudiante",
        example = "123456789"
    )

    @Column(name = "documentNumber")
    private String documentNumber;

    @NotNull(message = "La fecha de nacimiento es obligatoria")

    @Schema(
        description = "Fecha de nacimiento del estudiante",
        example = "2005-05-10"
    )

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @NotBlank(message = "El número telefónico es obligatorio")

    @Schema(
        description = "Número telefónico del estudiante",
        example = "3001234567"
    )

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Schema(
        description = "Nombre del acudiente",
        example = "Carlos Martinez"
    )

    @Column(name = "tutorName")
    private String tutorName;

    @Schema(
        description = "Número telefónico del acudiente",
        example = "3019876543"
    )

    @Column(name = "tutorPhoneNumber")
    private String tutorPhoneNumber;

    @NotBlank(message = "La dirección es obligatoria")

    @Schema(
        description = "Dirección del estudiante",
        example = "Calle 10 #20-30"
    )

    @Column(name = "direction")
    private String direction;

    @Schema(
        description = "Grupo asignado al estudiante"
    )

    @ManyToOne
    @JoinColumn(name = "group_id")
    private ClassGroup group;

    @Valid

    @Schema(
        description = "Usuario asociado al estudiante"
    )

    @OneToOne(cascade = {
    CascadeType.PERSIST,
    CascadeType.MERGE
    })
    @JoinColumn(name = "user_id")
    private SystemUser user;

    // GETTERS Y SETTERS

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
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

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public String getTutorPhoneNumber() {
        return tutorPhoneNumber;
    }

    public void setTutorPhoneNumber(String tutorPhoneNumber) {
        this.tutorPhoneNumber = tutorPhoneNumber;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public ClassGroup getGroup() {
        return group;
    }

    public void setGroup(ClassGroup group) {
        this.group = group;
    }

    public SystemUser getUser() {
        return user;
    }

    public void setUser(SystemUser user) {
        this.user = user;
    }
}