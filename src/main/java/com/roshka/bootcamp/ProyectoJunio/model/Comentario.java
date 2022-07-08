package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private Long id_comentario;
    private String descripcion;
    private Date fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "foto_id")
    private Foto foto;

    @OneToOne(mappedBy = "comentario")
    private Usuario usuario;

    @OneToMany(mappedBy = "comentario")
    private Set<ReaccionComentario> reaciones;

}
