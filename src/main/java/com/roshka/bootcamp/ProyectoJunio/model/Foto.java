package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(mappedBy = "foto")
    private Set<Comentario> comentarios = new HashSet<>();

    @OneToMany(mappedBy = "foto")
    private Set<ReaccionFoto> reacciones = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "id_album")
    private Album album;

    @OneToMany
    @JoinColumn(name = "foto_id", referencedColumnName = "id_foto")
    private List<Comentario> listaComentarios;
}
