package com.school.roller_speed.repositories;

import com.school.roller_speed.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository
        extends JpaRepository<Teacher, Long> {

    Teacher findByUser_UserId(Long userId);

}