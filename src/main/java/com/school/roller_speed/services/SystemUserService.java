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

@Service public class SystemUserService {

@Autowired private SystemUserRepository userRepository;
@Autowired private StudentRepository studentRepository;
@Autowired private TeacherRepository teacherRepository;

// LISTAR USUARIOS
public List<SystemUser> listarUsuarios() {
    return userRepository.findAll();
    }
    //================================================
    // GUARDAR USUARIO
    public void guardarUsuario(SystemUser user) {
        // SI ES EDICIÓN
        if (user.getUserId() != null) {
            SystemUser usuarioActual =
                    buscarPorId(user.getUserId());
            // SI LA CONTRASEÑA ES INVÁLIDA
            // CONSERVAR LA ANTERIOR
            if (
                user.getUserPassword() == null ||
                user.getUserPassword().trim().length() < 4
            ) {
                user.setUserPassword(
                        usuarioActual.getUserPassword()
                );
            }

        }
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
                studentRepository.findByUser_UserId(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado"+ id));
        if (student != null) {
            // Quitar relación con usuario
            student.setUser(null);
            // Guardar cambios
            studentRepository.save(student);
        }
        // Buscar docente asociado
        Teacher teacher =
                teacherRepository.findByUser_UserId(id);
        if (teacher != null) {
            // Quitar relación con usuario
            teacher.setUser(null);
            // Guardar cambios
            teacherRepository.save(teacher);
        }
        // Eliminar usuario
        userRepository.deleteById(id);

    }
}



