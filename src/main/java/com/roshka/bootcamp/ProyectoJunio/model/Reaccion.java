package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Reaccion {
    @Id
    @GeneratedValue
    private Long id_reaccion;
    private String nombre;
    private String icono;
}
