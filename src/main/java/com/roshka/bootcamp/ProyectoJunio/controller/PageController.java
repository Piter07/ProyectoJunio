package com.roshka.bootcamp.ProyectoJunio.controller;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.service.IAlbumService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import java.text.AttributedString;
import java.util.List;


@Controller
public class PageController {
    @Autowired
    private IAlbumService albumService;
    /*
    @GetMapping("/album/{pageNo}/{pageSize}")
    public List<Album> getPaginatedAlbums(@PathVariable int pageNo, @PathVariable int pageSize) {

        return albumService.findPaginated(pageNo, pageSize);
    }*/

    @GetMapping("/album/{pageNo}/{pageSize}")
    public String getPaginatedAlbums(@PathVariable int pageNo, @PathVariable int pageSize, Model model) {
        List<Album> albumes = albumService.findPaginated(pageNo, pageSize);
        model.addAttribute("albumes", albumes);
        return "albumes";
    }
}
