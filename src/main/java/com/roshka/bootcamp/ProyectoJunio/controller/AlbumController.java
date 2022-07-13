package com.roshka.bootcamp.ProyectoJunio.controller;


import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class AlbumController {

    private AlbumService albumService;
    @Autowired
    private FotoService fotoService;

    @Autowired
    private UsuarioService usuarioService;

    public AlbumController (AlbumService albumService) {this.albumService = albumService;}


    @GetMapping("/galeria/{id}")
    public String getAlbumById(@PathVariable long id, @RequestParam(name="pageAnt", required=false,defaultValue= "0") int pageAnt,Model model) throws Exception {
        Optional<Album> album = albumService.findById(id);
        List<Foto> fotos = fotoService.getFotos(id);
        if(album.isPresent()){
            model.addAttribute("titulo", album.get().getTitulo());
            model.addAttribute("descripcion", album.get().getDescripcion());
            model.addAttribute("fotos", fotos);
            model.addAttribute("pageAnt", pageAnt);
        }
        return "album-fotos";
    }

    //retorna la pagina del formulario album y recibe las imagenes al subir las fotos
    @GetMapping("/creacion-album")
    public String getFormFotos(){
        return "formulario-fotos";
    }
    //recibe los datos del formulario de creacion de album y crea un album en la base de datos por medio de AlbumService
    @PostMapping("/creacion-album")
    public String postFormAlbum(@ModelAttribute("objAlbum") AlbumDTO albumDTO){
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.existeUsuario(currentUserName);
        albumDTO.setUsuario(usuario);
        albumService.guardar(albumDTO);
//        if(!file.isEmpty()){
//            Path directorioImagenes= Paths.get("src/main/resources/static/img/ImagenesPrueba");
//            String rutaAbsoluta=directorioImagenes.toFile().getAbsolutePath();
//            try {
//                byte[] bytesImg=file.getBytes();
//                Path rutaCompleta=Paths.get(rutaAbsoluta+"/"+file.getOriginalFilename());
//                Files.write(rutaCompleta,bytesImg);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
        return "formulario-fotos";
    }
    @ModelAttribute("objAlbum")
    public AlbumDTO obtenerAlbumDTO() {
        return new AlbumDTO();
    }

//    @PostMapping("/creacion-album-foto/{idAlbum}")
//    public String postFormFoto(@RequestParam("idAlbum") idAlbum, @RequestParam("file") MultipartFile file){
//        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
//        Usuario usuario = usuarioService.existeUsuario(currentUserName);
//        albumDTO.setUsuario(usuario);
//        albumService.guardar(albumDTO);
////        if(!file.isEmpty()){
////            Path directorioImagenes= Paths.get("src/main/resources/static/img/ImagenesPrueba");
////            String rutaAbsoluta=directorioImagenes.toFile().getAbsolutePath();
////            try {
////                byte[] bytesImg=file.getBytes();
////                Path rutaCompleta=Paths.get(rutaAbsoluta+"/"+file.getOriginalFilename());
////                Files.write(rutaCompleta,bytesImg);
////            } catch (IOException e) {
////                throw new RuntimeException(e);
////            }
////        }
//        return "formulario-fotos";
//    }

}
