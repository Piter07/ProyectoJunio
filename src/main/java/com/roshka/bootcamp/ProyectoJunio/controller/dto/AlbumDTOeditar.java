package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class AlbumDTOeditar {
    private String titulo2;
    private String descripcion2;
    private Long idCategoria2;
    private String fechaEvento2;
}
