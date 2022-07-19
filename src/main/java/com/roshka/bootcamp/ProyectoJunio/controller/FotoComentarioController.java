package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.ComentarioDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.ReaccionDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioRegistroDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.AlbumService;
import com.roshka.bootcamp.ProyectoJunio.service.ComentarioService;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
import com.roshka.bootcamp.ProyectoJunio.service.ReaccionService;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class FotoComentarioController {

    @Autowired
    private FotoService fotoService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ReaccionService reaccionService;

    @GetMapping("/foto-comentario/{id}")
    public String getFotoComentarioById(@RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo, @PathVariable long id, Model model) throws Exception {
        Optional<Foto> foto = fotoService.findById(id);
        Optional<Album> album = albumService.findById(foto.get().getAlbum().getId_album());
        Optional<Foto> f;
        Long next=null;
        Long prev=null;
        try {
            f = fotoService.findById(id+1);
            if(album.get().getId_album() == f.get().getAlbum().getId_album()){
                next = f.get().getId_foto();
            }
        }catch (Exception e){

        }
        try {
            f = fotoService.findById(id-1);
            if(album.get().getId_album() == f.get().getAlbum().getId_album()){
                prev = f.get().getId_foto();
            }
        }catch (Exception e){

        }
        if (foto.isPresent()) {
            System.out.println(album);
            model.addAttribute("foto", foto.get());
            model.addAttribute("nroAlbum", foto.get().getAlbum().getId_album() );
            model.addAttribute("pageAnt", pageNo);
            model.addAttribute("comentarios", foto.get().getListaComentarios());
            model.addAttribute("id_Foto", id);
            model.addAttribute("titulo",foto.get().getAlbum().getTitulo());
            model.addAttribute("next",next);
            model.addAttribute("prev",prev);
            model.addAttribute("reacciones", reaccionService.list());
            /* -- ENVIO DE LOS EMOJIS A LOS COMENTARIOS -- */

        }
        return "foto-comentario";
    }

    @ModelAttribute("comentario")
    public ComentarioDTO retornaUnComentario() {
        return new ComentarioDTO();
    }

    @ModelAttribute("reaccion")
    public ReaccionDTO retornaUnaReaccion() {
        return new ReaccionDTO();
    }

    @PostMapping("foto-comentario")
    public String saveComentario(@ModelAttribute("comentario") ComentarioDTO comentarioDTO) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.existeUsuario(currentUserName);
        Foto foto = new Foto();
        System.out.println(comentarioDTO.getIdFoto());
        foto.setId_foto(Long.parseLong(comentarioDTO.getIdFoto()));

        try {
            /* comentario. id_foto y id_usuario */
            //comentarioDTO.setIdUsuario(usuario.getId_usuario().toString());
            comentarioDTO.setFoto(foto);
            comentarioDTO.setUsuario(usuario);
            if (!(comentarioDTO.getDescripcion().isEmpty())){
                comentarioService.guardarComentarioDTO(comentarioDTO);
            }

        } catch (Exception e) {
            System.out.println("error");
        }
        return "redirect:/foto-comentario/" + comentarioDTO.getIdFoto();
    }
    @PostMapping("foto-comentario-borrar")
    public String deleteComentario(@ModelAttribute("borrarComentario") ComentarioDTO comentarioDTO) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.existeUsuario(currentUserName);
        Foto foto = new Foto();
        foto.setId_foto(Long.parseLong(comentarioDTO.getIdFoto()));
        try {
            comentarioDTO.setUsuario(usuario);
            comentarioService.borrarComentarioDTO(comentarioDTO);
        } catch (Exception e) {
            System.out.println("error");
        }

        return "redirect:/foto-comentario/" + comentarioDTO.getIdFoto();
    }
}
