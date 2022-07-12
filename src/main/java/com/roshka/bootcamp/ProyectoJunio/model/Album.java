package com.roshka.bootcamp.ProyectoJunio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_album;
    private String titulo;
    private String descripcion;
    private Long id_categoria;

    //@Column(nullable = false, updatable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    //@CreatedDate
    private Date fechaCreacion;
    private Date fechaUltMod;
    private Date fechaEvento;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "album")
    @JsonIgnore
    private Set<Foto> fotos = new HashSet<Foto>();
}
