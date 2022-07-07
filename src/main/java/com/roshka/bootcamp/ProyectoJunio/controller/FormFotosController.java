package com.roshka.bootcamp.ProyectoJunio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormFotosController {
    @GetMapping("/FormFotos")
    public String getFormFotos(){

        return "formulario-fotos";
    }
}
