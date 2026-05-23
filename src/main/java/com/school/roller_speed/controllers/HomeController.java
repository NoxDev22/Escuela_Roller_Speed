package com.school.roller_speed.controllers;

import com.school.roller_speed.model.Docente;
import com.school.roller_speed.model.Grupo;
import com.school.roller_speed.model.Horario;
import com.school.roller_speed.repository.EstudianteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final EstudianteRepository estudianteRepository;

    public HomeController(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @GetMapping("/")
    public String Inicio(Model model, Principal principal) {
        // Reutilizamos la misma vista de estudiantes para la raíz cuando hay sesión activa
        populateEstudianteModel(model, principal);
        return "vista-de-estudiantes";
    }

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @GetMapping("/home")
    public String Home() {
        return "index";
    }

    @GetMapping("/admin")
    public String AdminHome() {
        return "index";
    }

    @GetMapping("/docente")
    public String DocenteHome() {
        return "index";
    }

    @GetMapping("/vista-de-estudiantes")
    public String EstudianteHome(Model model, Principal principal) {
        populateEstudianteModel(model, principal);
        return "vista-de-estudiantes";
    }

    private void populateEstudianteModel(Model model, Principal principal) {
        // Inicializar valores por defecto
        model.addAttribute("grupoNombre", "");
        model.addAttribute("grupoDescripcion", "");
        model.addAttribute("horarios", new ArrayList<>());
        model.addAttribute("docenteNombre", "");
        model.addAttribute("docenteCorreo", "");

        if (principal == null) {
            logger.warn("Principal es null en populateEstudianteModel");
            return;
        }

        String username = principal.getName();
        logger.info("Buscando estudiante para usuario: " + username);

        var estudianteOpt = estudianteRepository.findByUsuario_Username(username);
        
        if (!estudianteOpt.isPresent()) {
            logger.warn("No se encontró Estudiante para username: " + username);
            return;
        }

        var estudiante = estudianteOpt.get();
        logger.info("Estudiante encontrado: " + estudiante.getId());

        Grupo grupo = estudiante.getGrupo();
        if (grupo == null) {
            logger.warn("Grupo es null para estudiante: " + estudiante.getId());
            return;
        }

        logger.info("Grupo encontrado: " + grupo.getId() + " - " + grupo.getNombreGrupo());

        String grupoNombre = grupo.getNombreGrupo() != null ? grupo.getNombreGrupo() : "";
        String grupoDescripcion = "";

        List<Horario> horarios = new ArrayList<>();
        if (grupo.getHorarioGrupos() != null && !grupo.getHorarioGrupos().isEmpty()) {
            horarios = grupo.getHorarioGrupos().stream()
                    .map(hg -> hg.getHorario())
                    .collect(Collectors.toList());
            logger.info("Horarios encontrados: " + horarios.size());
        } else {
            logger.warn("HorarioGrupos es null o está vacío para grupo: " + grupo.getId());
        }

        Docente docente = grupo.getDocente();
        String docenteNombre = "";
        String docenteCorreo = "";
        
        if (docente != null) {
            docenteNombre = (docente.getNombre() != null ? docente.getNombre() : "") + " " + 
                           (docente.getApellidos() != null ? docente.getApellidos() : "");
            docenteCorreo = docente.getCorreo() != null ? docente.getCorreo() : "";
            logger.info("Docente encontrado: " + docenteNombre);
        } else {
            logger.warn("Docente es null para grupo: " + grupo.getId());
        }

        model.addAttribute("grupoNombre", grupoNombre);
        model.addAttribute("grupoDescripcion", grupoDescripcion);
        model.addAttribute("horarios", horarios);
        model.addAttribute("docenteNombre", docenteNombre);
        model.addAttribute("docenteCorreo", docenteCorreo);
    }


    @GetMapping("/mision")
    public String Mision() {
        return "mision";
    }

    @GetMapping("/vision")
    public String Vision() {
        return "vision";
    }

    @GetMapping("/servicios")
    public String Servicios() {
        return "servicios";
    }

    @GetMapping("/valores")
    public String Valores() {
        return "valores";
    }

    @GetMapping("/eventos")
    public String Eventos() {
        return "eventos";
    }
}