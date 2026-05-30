package com.school.roller_speed.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Teachers")
@Access(AccessType.FIELD)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "secondLastName")
    private String secondLastName;

    @Column(name = "documentNumber")
    private String documentNumber;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "Academic_title")
    private String academicTitle;

    @Column(name = "direction")
    private String direction;

    @OneToOne
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