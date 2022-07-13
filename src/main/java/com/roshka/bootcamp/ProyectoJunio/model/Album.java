package com.roshka.bootcamp.ProyectoJunio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.*;

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
    @JsonIgnore
    private Set<Foto> fotos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="categoria_id", referencedColumnName = "id_categoria")
    private Categoria categoria;

}
