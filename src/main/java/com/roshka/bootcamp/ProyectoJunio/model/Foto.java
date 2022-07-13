package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Foto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_foto;
    private String ruta;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id_album")
    private Album album;

    @OneToMany(mappedBy = "foto")
    private Set<Comentario> comentarios = new HashSet<>();

    @OneToMany(mappedBy = "foto")
    private Set<ReaccionFoto> reacciones = new HashSet<>();
}
