package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.ComentarioDTO;
import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioRegistroDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Comentario;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.service.ComentarioService;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/foto-comentario/{id}")
    public String getFotoComentarioById(@RequestParam(name="pageNo", required=false,defaultValue= "0") int pageNo, @PathVariable long id, Model model) throws Exception{
        Optional<Foto> foto = fotoService.findById(id);
        if(foto.isPresent()){
            model.addAttribute("foto", foto.get());
            model.addAttribute("nroAlbum", id);
            model.addAttribute("pageAnt", pageNo);
            model.addAttribute("comentarios",foto.get().getListaComentarios());
            model.addAttribute("id_foto", id);
        }
        return "foto-comentario";
    }
    @ModelAttribute("comentario")
    public ComentarioDTO retornaUnComentario(){
        return new ComentarioDTO();
    }
    @PostMapping("foto-comentario")
    public String saveComentario(@ModelAttribute("comentario") ComentarioDTO comentarioDTO) {
        try {
            /* comentario. id_foto y id_usuario */
            //comentarioService.guardarComentario(comentarioDTO);
            System.out.println(comentarioDTO);
        } catch (Exception e){
            System.out.println("error");
        }

    @PostMapping("/foto-comentario")
    public void saveComentario(@RequestBody Comentario comentario){
        comentarioService.guardarComentario(comentario);
    }
}
