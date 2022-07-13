package com.roshka.bootcamp.ProyectoJunio.model;

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

    @OneToMany(mappedBy = "album")
    private Set<Foto> fotos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @OneToOne
    private Categoria categoria;

}
