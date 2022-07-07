package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class ReaccionFoto {
    @Id
    @GeneratedValue
    private Long id_reaccion_foto;
    private Long id_foto;
    private Long id_reaccion;
    private Long id_usuario;
}
