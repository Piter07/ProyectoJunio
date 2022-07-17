package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comentario;
    private String descripcion;
    private Date fechaPublicacion;


    public Comentario() {
    }

    public Comentario(String descripcion, long id_foto, long id_usuario) {
        super();
        this.descripcion = descripcion;
    }

    @OneToMany(mappedBy = "comentario")
    private Set<ReaccionComentario> reaciones;


    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario comentarioUsuario;

    @ManyToOne
    @JoinColumn(name = "foto_id", referencedColumnName = "id_foto")
    private Foto foto;


    

}
