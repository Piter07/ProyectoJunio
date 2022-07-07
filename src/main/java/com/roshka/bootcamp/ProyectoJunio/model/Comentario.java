package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private Long id_comentario;
    private String descripcion;
    private Date fechaPublicacion;
    private Long id_usuario;
    private Long id_foto;
}
