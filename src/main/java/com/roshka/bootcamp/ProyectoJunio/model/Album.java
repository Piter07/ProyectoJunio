package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;
import java.util.*;

@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "album")
    private Set<Foto> fotos = new HashSet<Foto>();
}
