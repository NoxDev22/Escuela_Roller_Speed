package com.school.roller_speed;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String Inicio() {
        return "login";
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
    public String EstudianteHome() {
        return "vista-de-estudiantes";
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
