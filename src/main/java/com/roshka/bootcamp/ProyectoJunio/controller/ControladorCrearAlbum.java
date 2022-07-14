package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControladorCrearAlbum {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("mostrarVista")
    public String mostrarVista(){
        return "PruebaCrearAlbum";
    }
    @PostMapping("crearAlbum")
    public String crearAlbum(@ModelAttribute("objAlbum") AlbumDTO albumDTO){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.existeUsuario(currentUserName);
        albumDTO.setUsuario(usuario);
        Album albumGuardado = albumService.guardar(albumDTO);
        albumDTO.setId_album(albumGuardado.getId_album());
        return "DropzoneFoto";
    }
}
