package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.service.FotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class FotoComentarioController {

    @Autowired
    private FotoService fotoService;
    @GetMapping("/foto-comentario/{id}")
    public String getFotoComentarioById(@RequestParam(name="pageNo", required=false,defaultValue= "0") int pageNo, @PathVariable long id, Model model) throws Exception{
        Optional<Foto> foto = fotoService.findById(id);
        if(foto.isPresent()){
            model.addAttribute("foto", foto.get());
            model.addAttribute("nroAlbum", id);
            model.addAttribute("pageAnt", pageNo);
            model.addAttribute("comentarios",foto.get().getListaComentarios());
            model.addAttribute("titulo", foto.get().getAlbum().getTitulo());
        }
        return "foto-comentario";
    }
}
