package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import lombok.Data;

@Data
public class UsuarioRegistroDTO {
    private String email;
    private String password;
    private String claveConfirmar;
    private String nombre;
    private String apellido;
}
