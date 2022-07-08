package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @OneToMany(mappedBy = "reaccion")
    private Set<ReaccionFoto> fotos = new HashSet<>();
}
