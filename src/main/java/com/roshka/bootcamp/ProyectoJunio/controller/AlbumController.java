package com.roshka.bootcamp.ProyectoJunio.controller;


import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class AlbumController {

    private AlbumService albumService;
    private FotoService fotoService;

    public AlbumController (AlbumService albumService) {this.albumService = albumService;}

    @GetMapping("/album/{id}")
    public String getAlbumById(@PathVariable long id, Model model) throws Exception {
        Optional<Album> album = albumService.findById(id);

        if (album.isPresent()) {
            model.addAttribute("titulo", album.get().getTitulo());
            model.addAttribute("descripcion", album.get().getDescripcion());
            model.addAttribute("fechaEvento", album.get().getFechaEvento());
            model.addAttribute("usuario", album.get().getUsuario().getId_usuario());
            model.addAttribute("idEvento", album.get().getId_album());
        }
        return "album-fotos";
    }

    @GetMapping("/album")
    public String getAlbum(Model model) throws Exception {
        List<Album> albumes = albumService.list();
        model.addAttribute("albumes", albumes);
        return "albumes";
    }

    @GetMapping("/album-fotos/{id}")
        public List getFotosById(@PathVariable long id) throws Exception {

        return fotoService.getFotos(id);
    }
}
