package com.school.roller_speed.services;

import com.school.roller_speed.models.Teacher;
import com.school.roller_speed.repositories.TeacherRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    //===========================================================
    //Obteniendo el numero de entrenadores registrados
    public long getTotalTeachers() {
        return teacherRepository.count();
    }
    //===========================================================
    // Obteniendo lista de todos los entrenadores registrados
    public List<Teacher> getAllTeachers() {
    return teacherRepository.findAll();
    }
    //===========================================================
     // Obteniendo un docente por su ID
    public Teacher getTeacherById(Long id) {
        Optional<Teacher> optional =
                teacherRepository.findById(id);
        return optional.orElse(null);

    }
    //===========================================================
    // Guardando un docente
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    //===========================================================
    // Eliminando un docente
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);

    }
}
