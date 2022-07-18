package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.ComentarioDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioRegistroDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.ComentarioService;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class FotoComentarioController {

    @Autowired
    private FotoService fotoService;

    @Autowired
    private ComentarioService comentarioService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/foto-comentario/{id}")
    public String getFotoComentarioById(@RequestParam(name = "pageNo", required = false, defaultValue = "0") int pageNo, @PathVariable long id, Model model) throws Exception {
        Optional<Foto> foto = fotoService.findById(id);
        if (foto.isPresent()) {
            model.addAttribute("foto", foto.get());
            model.addAttribute("nroAlbum", id);
            model.addAttribute("pageAnt", pageNo);
            model.addAttribute("comentarios", foto.get().getListaComentarios());
            model.addAttribute("id_Foto", id);
        }
        return "foto-comentario";
    }

    @ModelAttribute("comentario")
    public ComentarioDTO retornaUnComentario() {
        return new ComentarioDTO();
    }

    @PostMapping("foto-comentario")
    public String saveComentario(@ModelAttribute("comentario") ComentarioDTO comentarioDTO) {
        final String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.existeUsuario(currentUserName);
        Foto foto = new Foto();
        foto.setId_foto(Long.parseLong(comentarioDTO.getIdFoto()));

        try {
            /* comentario. id_foto y id_usuario */
            //comentarioDTO.setIdUsuario(usuario.getId_usuario().toString());
            comentarioDTO.setFoto(foto);
            comentarioDTO.setUsuario(usuario);
            comentarioService.guardarComentarioDTO(comentarioDTO);
        } catch (Exception e) {
            System.out.println("error");
        }
        return "redirect:/foto-comentario/" + comentarioDTO.getIdFoto();
    }
}
