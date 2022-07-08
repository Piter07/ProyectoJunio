package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// marca la clase como controlador
@Controller
public class AlbumsController {

    private AlbumService albumService;

    public AlbumsController(AlbumService albumService) {
        super();
        this.albumService = albumService;
    }

    // maneja el request para la ruta base

    @GetMapping("/album-fotos")
    public String albumFoto(){
        return "album-fotos";
    }

}
