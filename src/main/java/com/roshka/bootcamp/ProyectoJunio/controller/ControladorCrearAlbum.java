package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
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

    @Autowired
    private FotoService fotoService;

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

    @PatchMapping("crearAlbum/{id}")
    public String editarAlbum(@PathVariable long id, Model model) throws Exception {
        return "album";
    }

    @GetMapping("/album/{id}/subir-fotos")
    public String subirFotos(@PathVariable long id, Model model) throws Exception {
        model.addAttribute("idAlbum", id);
        Optional<Album> albumActual = albumService.findById(id);
        if (albumActual.isPresent()) {
            model.addAttribute("album", albumActual.get());
        }
        return "DropzoneFoto";
    }

    @PostMapping("/album/{id}/subir-fotos")
    public String postFormFoto(@RequestParam("file") MultipartFile file, @PathVariable long id, Model model){
        Optional<Album> albumActual = albumService.findById(id);
        model.addAttribute("idAlbum", id);
        if (albumActual.isPresent()) {
            if(!file.isEmpty()){
                Path directorioImagenes= Paths.get("src/main/resources/public/"+  file.getOriginalFilename());
                String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
                try {
                    byte[] bytesImg=file.getBytes();
                    Path rutaCompleta=Paths.get(rutaAbsoluta);
                    Files.write(rutaCompleta, bytesImg);
//                     si todo salio bien guardamos la foto en la base de datos
                    fotoService.guardarFoto(albumActual.get(), file.getOriginalFilename());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return "redirect:/album/" + id ;
    }

}
