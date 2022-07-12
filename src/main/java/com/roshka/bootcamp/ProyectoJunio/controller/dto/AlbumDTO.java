package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class AlbumDTO {
    private String titulo;
    private String descripcion;
    private Long idCategoria;
    private String fechaEvento;
    private Date fechaCreacion;
    private Usuario usuario;
    private String username;
}