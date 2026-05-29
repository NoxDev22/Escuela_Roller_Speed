package com.school.roller_speed.controllers;

import com.school.roller_speed.models.SystemUser;
import com.school.roller_speed.models.Teacher;
import com.school.roller_speed.repositories.SystemUserRepository;
import com.school.roller_speed.repositories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SystemUserRepository systemUserRepository;

    @GetMapping("/admin/teachers/register")
    public String mostrarFormulario(Model model) {
        model.addAttribute("docente", new Teacher());
        
        // Find users that are not yet assigned to any teacher
        List<SystemUser> disponibles = systemUserRepository.findAvailableUsers();
        model.addAttribute("usuariosDisponibles", disponibles);
        
        return "registro-docente";
    }

    @PostMapping("/admin/teachers/save")
    public String guardarDocente(@ModelAttribute("docente") Teacher teacher) {
        if (teacher.getUser() != null && teacher.getUser().getUserId() != null) {
            systemUserRepository.findById(teacher.getUser().getUserId()).ifPresent(u -> {
                u.setUserAssigned(true);
                systemUserRepository.save(u);
                teacher.setUser(u);
            });
        } else {
            teacher.setUser(null);
        }
        
        teacherRepository.save(teacher);
        
        return "redirect:/admin/teachers/register?exito=true";
    }
}
