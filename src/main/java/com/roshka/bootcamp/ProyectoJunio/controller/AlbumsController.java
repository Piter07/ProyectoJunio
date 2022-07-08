package com.roshka.bootcamp.ProyectoJunio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// marca la clase como controlador
@Controller
public class AlbumsController {
    // maneja el request para la ruta base

    @GetMapping("/album-fotos")
    public String albumFoto(){
        return "album-fotos";
    }
}
