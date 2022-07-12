package com.roshka.bootcamp.ProyectoJunio.controller;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.service.IAlbumService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import java.text.AttributedString;
import java.util.List;


@Controller
public class PageController {
    @Autowired
    private IAlbumService albumService;

    @GetMapping("/album")
    public String getPaginatedAlbums(@RequestParam(name="pageNo") int pageNo, Model model) {
        int pageSize = 3;
        List<Album> albumes = albumService.findPaginated(pageNo, pageSize);
        int prev = (pageNo - 1);
        int next = (pageNo + 1);
        String pages[] = albumService.findPages(pageSize);
        if(prev < 0)
            prev = 0;
        if(next > pages.length - 1)
            next = pages.length - 1;
        model.addAttribute("albumes", albumes);
        model.addAttribute("pages",pages);
        model.addAttribute("prev",prev);
        model.addAttribute("next",next);
        return "albumes";
    }
}
