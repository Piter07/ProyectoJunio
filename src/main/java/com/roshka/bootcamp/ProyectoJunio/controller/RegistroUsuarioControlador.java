package com.roshka.bootcamp.ProyectoJunio.controller;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioRegistroDTO;
import com.roshka.bootcamp.ProyectoJunio.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {
    private UsuarioService usuarioService;

    public RegistroUsuarioControlador(UsuarioService usuarioService) {
        super();
        this.usuarioService = usuarioService;
    }

    @ModelAttribute("usuario")
    public UsuarioRegistroDTO retornaNuevoUsuarioRegistroDTO() {
        return new UsuarioRegistroDTO();
    }

    @GetMapping
    public String mostrarFomrularioDeRegistro() {
        return "formulario-usuario";
    }

    @PostMapping
    public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
        String correo = registroDTO.getCorreo();
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]+@roshka.com$");
        Matcher matcher = pattern.matcher(correo);
        if (!matcher.matches()) {
            return "redirect:/registro?error=correo-invalido";
        }
        if (!registroDTO.getClave().equals(registroDTO.getClaveConfirmar())) {
            return "redirect:/registro?error=clave-no-coincide";
        }
        if (usuarioService.existeUsuario(correo)) {
            return "redirect:/registro?error=correo-ya-existe";
        }
        usuarioService.guardar(registroDTO);
        return "redirect:/registro?exito";
    }
}