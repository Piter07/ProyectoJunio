package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.model.Rol;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.RolService;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class ValidarCorreoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    @GetMapping("/verificacion")
    public String validarUsuario(@RequestParam String token, @RequestParam String correo) {

        Usuario usuario = usuarioService.existeUsuario(correo);

        /* si no existe el usuario o el token está vacio */
        if(usuario == null || token.equals("")) {
            return "redirect:/login?err001";
        }

        /* si la cuenta ya está activa */
        if(usuario.getEstado() == "A") {
            return "redirect:/login?err002";
        }

        /* si los token son distintos */
        if( !token.equals(usuario.getTokenVerificacion()) ) {
            return "redirect:/login?err001";
        }

        /* actuliza los siguientes atributos */
        try {
            Rol rol = rolService.getDefaultRol();

            usuario.getRoles().add(rol); // asignar el rol por defecto al usuario
            usuario.setTokenVerificacion("");
            usuario.setEstado("A");

            /* guardar cambios */
            if(usuarioService.guardar(usuario) == null) {
                System.out.println("\nHa ocurrido un error inesperado al activar el usuario");
                return "redirect:/login?err001";
            }

            return "redirect:/login?validado";
        } catch (Exception e) {
            return "redirect:/login?err001";
        }
    }

}
