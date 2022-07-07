package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Data
@Entity
public class Album {
    @Id
    @GeneratedValue
    private Long id_album;
    private String titulo;
    private String descripcion;
    private Long id_categoria;
    private Date fechaCreacion;
    private Date fechaUltMod;
    private Date fechaEvento;
    private Long id_usuario;
}