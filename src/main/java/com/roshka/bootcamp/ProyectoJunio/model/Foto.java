package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Foto {
    @Id
    @GeneratedValue
    private Long id_foto;
    private String ruta;
    private String descripcion;
    private Long id_album;
    @OneToMany(mappedBy = "foto")
    private Set<ReaccionFoto> reacciones = new HashSet<>();
}
