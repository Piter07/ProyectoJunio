package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Foto {
    @Id
    @GeneratedValue
    private Long id_foto;
    private String ruta;
    private String descripcion;
    private Long id_album;
}
