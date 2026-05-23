package com.school.roller_speed.controllers;

import com.school.roller_speed.models.SystemUser;
import com.school.roller_speed.repositories.UserRoleRepository;
import com.school.roller_speed.services.SystemUserService;

import com.school.roller_speed.models.Teacher;
import com.school.roller_speed.models.Student;
import com.school.roller_speed.repositories.TeacherRepository;
import com.school.roller_speed.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SystemUserController {

    @Autowired
    private SystemUserService userService;

    @Autowired
    private UserRoleRepository roleRepository;

    @Autowired
private TeacherRepository teacherRepository;

@Autowired
private StudentRepository studentRepository;

    // LISTAR USUARIOS

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {

        List<SystemUser> listaUsuarios =
                userService.listarUsuarios();

        model.addAttribute(
                "usuarios",
                listaUsuarios
        );

        return "admin/usuarios";
    }

    // FORMULARIO NUEVO USUARIO

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {

        model.addAttribute(
                "usuario",
                new SystemUser()
        );

        model.addAttribute(
                "roles",
                roleRepository.findAll()
        );

        return "admin/nuevo_usuario";
    }

    // GUARDAR USUARIO

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(
            @ModelAttribute SystemUser usuario
    ) {

        userService.guardarUsuario(usuario);

        return "redirect:/usuarios";
    }

    // VER DETALLE USUARIO

    // VER DETALLE USUARIO

@GetMapping("/usuarios/detalle/{id}")
public String verDetalleUsuario(
        @PathVariable Long id,
        Model model
) {

    SystemUser usuario =
            userService.buscarPorId(id);

    model.addAttribute(
            "usuario",
            usuario
    );

    String rol =
            usuario.getRole().getRolName();

    // SI ES DOCENTE

    if (rol.equalsIgnoreCase("Docente")) {

        Teacher teacher =
                teacherRepository.findByUser_UserId(id);

        model.addAttribute(
                "teacher",
                teacher
        );
    }

    // SI ES ESTUDIANTE

    if (rol.equalsIgnoreCase("Estudiante")) {

        Student student =
                studentRepository.findByUser_UserId(id);

        model.addAttribute(
                "student",
                student
        );
    }

    return "admin/detalle_usuario";
}

    // FORMULARIO EDITAR

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(
            @PathVariable Long id,
            Model model
    ) {

        SystemUser usuario =
                userService.buscarPorId(id);

        model.addAttribute(
                "usuario",
                usuario
        );

        model.addAttribute(
                "roles",
                roleRepository.findAll()
        );

        return "admin/editar_usuario";
    }

    // ACTUALIZAR USUARIO

    @PostMapping("/usuarios/actualizar")
    public String actualizarUsuario(
            @ModelAttribute SystemUser usuario
    ) {

        userService.guardarUsuario(usuario);

        return "redirect:/usuarios";
    }

    // ELIMINAR USUARIO

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(
            @PathVariable Long id
    ) {

        userService.eliminarUsuario(id);

        return "redirect:/usuarios";
    }
}