package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Reaccion {
    @Id
    @GeneratedValue
    private Long id_reaccion;
    private String nombre;
    private String icono;

    /* Una foto tiene varias reaciones y una reacion puede estar en varias fotos */
    @OneToMany(mappedBy = "reaccion")
    private Set<ReaccionFoto> fotos = new HashSet<>();

    /* Un comentario tiene varias reaciones y una reacion puede estar en varios comentarios */
    @OneToMany(mappedBy = "reaccion")
    private Set<ReaccionComentario> comentarios = new HashSet<>();

}
