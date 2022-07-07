package com.roshka.bootcamp.ProyectoJunio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormFotosController {
    @GetMapping("/FormFotos")
    public String getFormFotos(){
        return "formulario-fotos";
    }
    @PostMapping("/FormFotos")
    public String postFormFotos(){
        return "formulario-fotos";
    }
}
