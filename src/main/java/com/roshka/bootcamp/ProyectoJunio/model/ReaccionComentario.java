package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ReaccionComentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reaccion_comentario;

    @ManyToOne
    @JoinColumn(name = "comentario_id", referencedColumnName = "id_comentario")
    private Comentario comentario;

    @ManyToOne
    @JoinColumn(name = "reaccion_id", referencedColumnName = "id_reaccion")
    private Reaccion reaccion;

    @OneToOne
    private Usuario usuario;

}
