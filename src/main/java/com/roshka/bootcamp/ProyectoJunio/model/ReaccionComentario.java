package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ReaccionComentario {
    @Id
    @GeneratedValue
    private Long id_reaccion_comentario;

    @ManyToOne
    @JoinColumn(name = "comentario_id", referencedColumnName = "id_comentario")
    private Comentario comentario;

    @ManyToOne
    @JoinColumn(name = "reaccion_id", referencedColumnName = "id_reaccion")
    private Reaccion reaccion;

    @OneToOne(mappedBy = "reaccionComentario")
    private Usuario usuario;

}
