package com.school.roller_speed.services;

import com.school.roller_speed.models.Teacher;
import com.school.roller_speed.repositories.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    // LISTAR DOCENTES

    public List<Teacher> listarDocentes() {

        return teacherRepository.findAll();

    }

    // BUSCAR POR ID

    public Teacher buscarPorId(Long id) {

        Optional<Teacher> optional =
                teacherRepository.findById(id);

        return optional.orElse(null);

    }

    // GUARDAR DOCENTE

    public Teacher guardarDocente(Teacher teacher) {

        return teacherRepository.save(teacher);

    }

    // ELIMINAR DOCENTE

    public void eliminarDocente(Long id) {

        teacherRepository.deleteById(id);

    }

}