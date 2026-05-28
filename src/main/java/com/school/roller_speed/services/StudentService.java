package com.school.roller_speed.services;

import com.school.roller_speed.models.Student;
import com.school.roller_speed.models.SystemUser;
import com.school.roller_speed.models.UserRole;

import com.school.roller_speed.repositories.StudentRepository;
import com.school.roller_speed.repositories.UserRoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRoleRepository roleRepository;

    // LISTAR ESTUDIANTES

    public List<Student> listarEstudiantes() {

        return studentRepository.findAll();

    }

    // BUSCAR POR ID

    public Student buscarPorId(Long id) {

        Optional<Student> optional =
                studentRepository.findById(id);

        return optional.orElse(null);

    }

    // GUARDAR ESTUDIANTE

    public Student guardarEstudiante(Student student) {

        // OBTENER USUARIO

        SystemUser user = student.getUser();

        // ASIGNAR ROL ESTUDIANTE AUTOMÁTICAMENTE
        // ID 3 = ESTUDIANTE

        UserRole rolEstudiante =
                roleRepository.findById(3L).orElse(null);

        user.setRole(rolEstudiante);

        // USUARIO NO ASIGNADO POR DEFECTO

        user.setUserAssigned(false);

        return studentRepository.save(student);

    }

    // ELIMINAR ESTUDIANTE

    public void eliminarEstudiante(Long id) {

        studentRepository.deleteById(id);

    }

}