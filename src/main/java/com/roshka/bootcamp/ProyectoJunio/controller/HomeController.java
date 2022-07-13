package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// marca la clase como controlador
@Controller
public class HomeController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/")
    public String home() {
        // retorna el nombre de la vista
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        // retorna el nombre de la vista
        return "login";
    }


    /*
    @GetMapping("/registro")
    public String registro() {
        // retorna el nombre de la vista
        return "formulario-usuario";

    }
     */

}