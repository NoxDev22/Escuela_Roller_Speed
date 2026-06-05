package com.school.roller_speed.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.school.roller_speed.models.SystemUser;
import com.school.roller_speed.models.Teacher;
import com.school.roller_speed.repositories.SystemUserRepository;
import com.school.roller_speed.repositories.TeacherRepository;

@Controller
public class TeacherController {
     @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SystemUserRepository systemUserRepository;

    @GetMapping("/entrenador/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("docente", new Teacher());
        
        // Find users that are not yet assigned to any teacher
        List<SystemUser> disponibles = systemUserRepository.findAvailableUsers();
        model.addAttribute("usuariosDisponibles", disponibles);
        
        return "admin/agregar_entrenador";
    }

    @PostMapping("/entrenador/guardar")
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
        
        return "redirect:/entrenador/agregar?exito=true";
    }
    /*==============================================*/
    // EDITAR DOCENTE
    @GetMapping("/docentes/editar/{id}")
    public String editarDocente(
        @PathVariable Long id,
        Model model) {

    Teacher teacher = teacherRepository.findById(id)
            .orElseThrow(() ->
                    new RuntimeException("Docente no encontrado"));

    model.addAttribute("teacher", teacher);

    return "admin/editar_docente";
    }

    /*==============================================*/
    // ACTUALIZAR DOCENTE
   @PostMapping("/docentes/actualizar")
        public String actualizarDocente(
        @ModelAttribute Teacher teacher,
        RedirectAttributes redirectAttributes) {

    Teacher existing = teacherRepository.findById(teacher.getTeacherId())
            .orElseThrow(() ->
                    new RuntimeException("Docente no encontrado"));

    existing.setFirstName(teacher.getFirstName());
    existing.setSecondName(teacher.getSecondName());
    existing.setLastName(teacher.getLastName());
    existing.setSecondLastName(teacher.getSecondLastName());
    existing.setDocumentNumber(teacher.getDocumentNumber());
    existing.setBirthdate(teacher.getBirthdate());
    existing.setGmail(teacher.getGmail());
    existing.setPhoneNumber(teacher.getPhoneNumber());
    existing.setAcademicTitle(teacher.getAcademicTitle());
    existing.setDirection(teacher.getDirection());

    teacherRepository.save(existing);

     redirectAttributes.addFlashAttribute(
            "mensajeExito",
            "Docente actualizado"
    );

    return "redirect:/usuarios";
    }
    /*==============================================*/
    // ELIMINAR DOCENTE
    @GetMapping("/docentes/eliminar/{id}")
    public String eliminarDocente(@PathVariable Long id) {

    Teacher teacher = teacherRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Docente no encontrado"));
        //Eliminar docente y cambiar al usuario a inactivo
    if (teacher.getUser() != null) {
        SystemUser user = teacher.getUser();
        user.setUserAssigned(false);
        systemUserRepository.save(user);
    }

    teacherRepository.delete(teacher);

    return "redirect:/usuarios";
    }


}
