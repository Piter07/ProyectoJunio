package com.roshka.bootcamp.ProyectoJunio.controller;


import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
//@Data
public class AlbumController {

    private AlbumService albumService;

    public AlbumController (AlbumService albumService) {this.albumService = albumService;}


    @GetMapping("/album/{id}")
    public String getAlbumById(@PathVariable long id, Model model) throws Exception {
        Optional<Album> album = albumService.findById(id);

        if (album.isPresent()) {
            model.addAttribute("titulo", album.get().getTitulo());
            model.addAttribute("descripcion", album.get().getDescripcion());
            model.addAttribute("fechaEvento", album.get().getFechaEvento());
            model.addAttribute("usuario", album.get().getUsuario().getId_usuario());
        }
        return "album-fotos";
    }

    @GetMapping("/album")
    public String getAlbum(Model model) throws Exception {
        List<Album> albumes = albumService.list();
        model.addAttribute("albumes", albumes);
        return "albumes";
    }

    @GetMapping("/album-fotos")
    public String albumFoto(){

        return "album-fotos";
    }

    //retorna la pagina del formulario album y recibe las imagenes al subir las fotos
    @GetMapping("/creacion-album")
    public String getFormFotos(){
        return "formulario-fotos";
    }
    @PostMapping("/creacion-album")
    public String postFormFotos(@ModelAttribute("album") AlbumDTO albumDTO, @RequestParam("file") MultipartFile multipartFile){
        albumService.guardar(albumDTO);
        return "formulario-fotos";
    }
}
