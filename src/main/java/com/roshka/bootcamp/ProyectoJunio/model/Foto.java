package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Foto {
    @Id
    @GeneratedValue
    private Long id_foto;
    private String ruta;
    private String descripcion;
    @ManyToOne
    @JoinColumn
    private Album album;
}
