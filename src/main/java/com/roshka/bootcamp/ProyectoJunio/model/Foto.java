package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Foto {
    @Id
    @GeneratedValue
    private Long id_foto;
    private String ruta;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @OneToMany(mappedBy = "foto")
    private Set<Comentario> comentarios = new HashSet<>();

    @OneToMany(mappedBy = "foto")
    private Set<ReaccionFoto> reacciones = new HashSet<>();
}
