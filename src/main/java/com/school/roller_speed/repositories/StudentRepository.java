package com.school.roller_speed.repositories;

import com.school.roller_speed.models.Student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Long> {
    Optional<Student> findByUser_UserId(Long userId);

}