package com.roshka.bootcamp.ProyectoJunio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

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
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    private Set<Foto> fotos = new HashSet<Foto>();
}
