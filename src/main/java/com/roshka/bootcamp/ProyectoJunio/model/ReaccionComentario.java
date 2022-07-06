package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ReaccionComentario {
    @Id
    @GeneratedValue
    private Long id_reaccion_comentario;
    private Long id_comentario;
    private Long id_reaccion;
    private Long id_usuario;
}
