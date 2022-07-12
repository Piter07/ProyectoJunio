package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AlbumDTO {
    private String titulo;
    private String descripcion;
    private Long idCategoria;
    private String fechaEvento;
    private Date fechaCreacion;
//    private Long idUsuario;
}