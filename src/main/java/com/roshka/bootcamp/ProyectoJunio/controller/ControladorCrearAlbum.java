package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
import com.roshka.bootcamp.ProyectoJunio.service.CategoriaService;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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
        System.out.println("Creamos un album exitosamente");
        return "redirect:/album/" + albumGuardado.getId_album() + "/subir-fotos";
    }

    @GetMapping("/album/{id}/subir-fotos")
    public String subirFotos(@PathVariable long id, Model model) throws Exception {
        Optional<Album> albumActual = albumService.findById(id);
        if (albumActual.isPresent()) {
            model.addAttribute("album", albumActual.get());
        }
        return "DropzoneFoto";
    }

    @PostMapping("/album/{id}/subir-fotos")
    public String postFormFoto(@PathVariable long id,@RequestParam("file") MultipartFile file){
        Optional<Album> albumActual = albumService.findById(id);
        if (albumActual.isPresent()) {
            if(!file.isEmpty()){
                Path directorioImagenes= Paths.get("src/main/resources/media/"+ albumActual.get().getId_album() + file.getOriginalFilename());
                String rutaAbsoluta=directorioImagenes.toFile().getAbsolutePath();
                try {
                    byte[] bytesImg=file.getBytes();
                    Path rutaCompleta=Paths.get(rutaAbsoluta+"/"+file.getOriginalFilename());
                    Files.write(rutaCompleta,bytesImg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "redirect:album/" + albumActual.get().getId_album() ;
    }

}
