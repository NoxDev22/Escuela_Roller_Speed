package com.school.roller_speed.controllers;

import com.school.roller_speed.models.Student;
import com.school.roller_speed.models.SystemUser;
import com.school.roller_speed.repositories.StudentRepository;
import com.school.roller_speed.repositories.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SystemUserRepository systemUserRepository;

    @GetMapping("/admin/students/register")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Student());

        List<SystemUser> disponibles = systemUserRepository.findAvailableUsers();
        model.addAttribute("usuariosDisponibles", disponibles);

        return "registro-estudiante";
    }

    @PostMapping("/admin/students/save")
    public String guardarEstudiante(@ModelAttribute("estudiante") Student student) {
        if (student.getUser() != null && student.getUser().getUserId() != null) {
            systemUserRepository.findById(student.getUser().getUserId()).ifPresent(student::setUser);
        } else {
            student.setUser(null);
        }

        studentRepository.save(student);

        return "redirect:/admin/students/register?exito=true";
    }
}
