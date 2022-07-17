package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ComentarioDTO {
    private String descripcion;
    private String idFoto;
    private String idUsuario;
}
