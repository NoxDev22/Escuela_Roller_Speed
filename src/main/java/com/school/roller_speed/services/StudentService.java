package com.school.roller_speed.services;

import com.school.roller_speed.dto.StudentGroupDto;
import com.school.roller_speed.models.ClassGroup;
import com.school.roller_speed.models.Schedule;
import com.school.roller_speed.models.Student;
import com.school.roller_speed.models.SystemUser;
import com.school.roller_speed.models.Teacher;
import com.school.roller_speed.models.UserRole;
import com.school.roller_speed.repositories.StudentRepository;
import com.school.roller_speed.repositories.UserRoleRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    //==============================================================================
    //Obteniendo el total de estudiantes registrados
    public long getTotalStudents() {
        return studentRepository.count();
    }
    //==============================================================================
    // Obteniendo lista de todos los estudiantes registrados
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    //==============================================================================
    // Buscar un estudiante por ID
    public Student getStudentById(Long id) {
        Optional<Student> optional =
                studentRepository.findById(id);
        return optional.orElse(null);

    }
    //==============================================================================
    // Guardando un estudiante
    public Student saveStudent(Student student) {
        // OBTENER USUARIO
        SystemUser user = student.getUser();
        // ASIGNAR ROL ESTUDIANTE AUTOMÁTICAMENTE
        // ID 3 = ESTUDIANTE
        UserRole rolEstudiante =
                userRoleRepository.findById(3L).orElse(null);
        user.setRole(rolEstudiante);
        // USUARIO NO ASIGNADO POR DEFECTO
        user.setUserAssigned(false);
        return studentRepository.save(student);

    }
    //==============================================================================
    // Eliminando un estudiante
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    //==============================================================================
    //Obteniendo información para la vista de estudiantes
    public StudentGroupDto getStudentInformation(Long userId) {
        Student student = studentRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado "+userId));
        ClassGroup group = student.getGroup();
        Teacher teacher = group.getTeacher();
        Schedule schedule = group.getSchedule();
        List<String> classmates = group.getStudents()
                .stream()
                .filter(s -> !s.getStudentId().equals(student.getStudentId()))
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .toList();
        String studentName = student.getFirstName() + " " + student.getLastName();
        String teacherName = teacher.getFirstName() + " " + teacher.getLastName();

        return new StudentGroupDto(
                studentName,
                group.getGroupName(),
                group.getGroupLevel(),
                teacherName,
                schedule.getScheduleClass(),
                classmates
        );
    }

        
}