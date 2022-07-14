package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Foto {
    @Id
    @GeneratedValue
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
