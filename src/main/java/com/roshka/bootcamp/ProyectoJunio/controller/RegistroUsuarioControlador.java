package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioRegistroDTO;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

    @Autowired
    private UsuarioService usuarioService;

    /*
    // Lo mismo que la anotacion Autowired
    public RegistroUsuarioControlador(UsuarioService usuarioService) {
        super();
        this.usuarioService = usuarioService;
    }
    */

    /* Si se usa el tag th:object en el formulario crea un objecto del tipo
    *  UsuarioRegistroDTO para rellenar los datos del formulario en el objeto.
    * */
    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornaNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String mostrarFomrularioDeRegistro() {
        return "registro";
    }

    /* Trae el objecto 'usuario' desde el formulario para guardarlo en la base de datos */
    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
        try {
            usuarioService.guardar(registroDTO);
            return "redirect:/registro?exito";
        } catch (Exception e) {
            return "redirect:/registro?error";
        }
    }

}
