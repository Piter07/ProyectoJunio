package com.roshka.bootcamp.ProyectoJunio.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.UsuarioRegistroDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;

import java.util.List;
import java.util.Set;

public interface UsuarioServiceInterface extends UserDetailsService {

    public Usuario guardar(UsuarioRegistroDTO registroDTO);

    public List<Usuario> listarUsuarios();
    
}
