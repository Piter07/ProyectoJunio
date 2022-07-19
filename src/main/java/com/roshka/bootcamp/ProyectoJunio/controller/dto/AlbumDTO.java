package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import lombok.Data;

import java.util.Date;

@Data
public class AlbumDTO {
    private Long id_album;
    private String titulo;
    private String descripcion;
    private Long idCategoria;
    private String fechaEvento;
    private Date fechaCreacion;
    private Usuario usuario;
    private String nombreCategoria;

    public AlbumDTO(){

    }
    public AlbumDTO(Album album) {
        this.id_album = album.getId_album();
        this.titulo = album.getTitulo();
        this.descripcion = album.getDescripcion();
        this.idCategoria = album.getCategoria().getId_categoria();
        this.nombreCategoria = album.getCategoria().getNombre();
        this.fechaEvento = album.getFechaEvento().toString();
        this.fechaCreacion = album.getFechaCreacion();
//        this.usuario = album.getUsuario();
    }
}