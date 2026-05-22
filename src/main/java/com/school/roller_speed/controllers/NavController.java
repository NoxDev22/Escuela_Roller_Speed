package com.school.roller_speed.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NavController {

    @GetMapping("/nav")
    public String getMethodName() {
        return "index";
    }
}
