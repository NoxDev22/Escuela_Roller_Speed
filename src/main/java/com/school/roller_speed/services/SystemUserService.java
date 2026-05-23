package com.school.roller_speed.services;

import com.school.roller_speed.models.Student;
import com.school.roller_speed.models.SystemUser;
import com.school.roller_speed.models.Teacher;
import com.school.roller_speed.repositories.StudentRepository;
import com.school.roller_speed.repositories.SystemUserRepository;
import com.school.roller_speed.repositories.TeacherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SystemUserService {

    @Autowired
    private SystemUserRepository userRepository;

    @Autowired
    private StudentRepository StudentRepository;

    @Autowired
    private TeacherRepository TeacherRepository;

// LISTAR USUARIOS

public List<SystemUser> listarUsuarios() {
    return userRepository.findAll();
    }

// GUARDAR USUARIO

public void guardarUsuario(SystemUser user) {

    // Usuario disponible por defecto

    user.setUserAssigned(false);

    userRepository.save(user);

}

// BUSCAR POR ID

public SystemUser buscarPorId(Long id) {
    Optional<SystemUser> optional =
    userRepository.findById(id);

    return optional.orElse(null);
    }

    // ELIMINAR

    @Transactional
    public void eliminarUsuario(Long id) {

        // Buscar estudiante asociado

        Student student =
                StudentRepository.findByUser_UserId(id);

        if (student != null) {

            // Quitar relación con usuario

            student.setUser(null);

            // Guardar cambios

            StudentRepository.save(student);

        }

        // Buscar docente asociado

        Teacher teacher =
                TeacherRepository.findByUser_UserId(id);

        if (teacher != null) {

            // Quitar relación con usuario

            teacher.setUser(null);

            // Guardar cambios

            TeacherRepository.save(teacher);

        }

        // Eliminar usuario

        userRepository.deleteById(id);

    }

}





