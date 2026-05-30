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

import com.school.roller_speed.dto.StudentGroupDto;
import com.school.roller_speed.models.Student;
import com.school.roller_speed.models.SystemUser;
import com.school.roller_speed.repositories.StudentRepository;
import com.school.roller_speed.repositories.SystemUserRepository;
import com.school.roller_speed.services.StudentService;

@Controller
public class StudentController {
    @Autowired
     private StudentService studentService;
     @Autowired
     private SystemUserRepository systemUserRepository;
     @Autowired
     private StudentRepository studentRepository;
     @Autowired
    private com.school.roller_speed.services.GroupService groupService;


      public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*==============================================*/
    @GetMapping("/estudiante/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estudiante", new Student());

        List<SystemUser> disponibles = systemUserRepository.findAvailableUsers();
        model.addAttribute("usuariosDisponibles", disponibles);

        return "admin/agregar_estudiante";
    }
    /*==============================================*/
    @GetMapping("/estudiante/{userId}")
    public String getStudent(@PathVariable Long userId,
                             Model model) {
                                
        StudentGroupDto studentInfo =
                studentService.getStudentInformation(userId);

        model.addAttribute("studentInfo", studentInfo);

        return "/student/grupos_estudiante";
    }
    /*==============================================*/
    @PostMapping("estudiantes/guardar")
    public String guardarEstudiante(@ModelAttribute("estudiante") Student student) {
        if (student.getUser() != null && student.getUser().getUserId() != null) {
            systemUserRepository.findById(student.getUser().getUserId()).ifPresent(u -> {
                u.setUserAssigned(true);
                systemUserRepository.save(u);
                student.setUser(u);
            });
        } else {
            student.setUser(null);
        }
        studentRepository.save(student);

        return "redirect:/estudiantes?exito=true";
    }
    /*==============================================*/
    //Editar estudiante
    @GetMapping("/estudiante/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model) {

    Student student = studentService.getStudentById(id);
    model.addAttribute("student", student);

    List<SystemUser> disponibles = systemUserRepository.findAvailableUsers();
    model.addAttribute("usuariosDisponibles", disponibles);

    model.addAttribute("groups", groupService.getAllGroups());

    return "admin/editar_estudiante";
    }
    /*==============================================*/
    //actualizar estudiante
   @PostMapping("/estudiante/actualizar")
    public String actualizarEstudiante(
        @ModelAttribute Student student,
        RedirectAttributes redirectAttributes) {

    Student existing = studentService.getStudentById(student.getStudentId());

    existing.setFirstName(student.getFirstName());
    existing.setSecondName(student.getSecondName());
    existing.setLastName(student.getLastName());
    existing.setSecondLastName(student.getSecondLastName());
    existing.setDocumentType(student.getDocumentType());
    existing.setDocumentNumber(student.getDocumentNumber());
    existing.setBirthdate(student.getBirthdate());
    existing.setPhoneNumber(student.getPhoneNumber());
    existing.setTutorName(student.getTutorName());
    existing.setTutorPhoneNumber(student.getTutorPhoneNumber());
    existing.setDirection(student.getDirection());
    existing.setGroup(student.getGroup());

    studentRepository.save(existing);

    redirectAttributes.addFlashAttribute(
            "mensajeExito",
            "Estudiante actualizado"
    );

    return "redirect:/usuarios";
    }

    /*==============================================*/
    // Eliminar estudiante
    @GetMapping("/estudiante/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {

    Student student = studentService.getStudentById(id);

    if (student.getUser() != null) {

        SystemUser user = student.getUser();

        user.setUserAssigned(false);
        systemUserRepository.save(user);

        // romper relación para evitar eliminar el usuario
        student.setUser(null);

        studentRepository.save(student);
    }

    studentRepository.delete(student);

    return "redirect:/usuarios";
    }


}



