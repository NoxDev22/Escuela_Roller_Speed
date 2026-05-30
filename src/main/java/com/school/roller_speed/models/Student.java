package com.school.roller_speed.models;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder.In;

import java.time.LocalDate;

@Entity
@Table(name = "Students")
@Access(AccessType.FIELD)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "secondName")
    private String secondName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "secondLastName")
    private String secondLastName;

    @Column(name = "document_type")
    private String documentType;

    @Column(name = "documentNumber")
    private String documentNumber;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "tutorName")
    private String tutorName;

    @Column(name = "tutorPhoneNumber")
    private String tutorPhoneNumber;

    @Column(name = "direction")
    private String direction;

    @OneToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private ClassGroup group;

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