package com.roshka.bootcamp.ProyectoJunio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// marca la clase como controlador
@Controller
public class AlbumsController {
    // maneja el request para la ruta base
    @GetMapping("/album")
    public String home() {
        // retorna el nombre de la vista
        return "albumes";
    }
    @GetMapping("/album-fotos")
    public String albumFoto(){
        return "album-fotos";
    }

    //retorna la pagina del formulario album y recibe las imagenes al subir las fotos
    @GetMapping("/CreacionAlbum")
    public String getFormFotos(){
        return "formulario-fotos";
    }
    @PostMapping("/CreacionAlbum")
    public String postFormFotos(){
        System.out.println("hola estamos en post");
        return "formulario-fotos";
    }
}
