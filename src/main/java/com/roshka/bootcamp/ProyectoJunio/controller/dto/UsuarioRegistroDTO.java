package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import lombok.Data;

@Data
public class UsuarioRegistroDTO {
    private String correo;
    private String clave;
    private String claveConfirmar;
    private String nombre;
    private String apellido;
}
