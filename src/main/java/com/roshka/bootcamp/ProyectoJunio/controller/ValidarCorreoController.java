package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ValidarCorreoController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/verificacion")
    public String validarUsuario(@RequestParam String token, @RequestParam String correo) {

        System.out.println(token + " >>> " + correo);

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

        usuario.setTokenVerificacion("");
        usuario.setEstado("A");

        /* guardar cambios */
        if(usuarioService.guardar(usuario) == null) {
            System.out.println("\nHa ocurrido un error inesperado");
            return "redirect:/login?err001";
        } else {
            System.out.println("\nUsuario actualizado correctamente");
        }

        return "redirect:/login?validado";
    }

}
