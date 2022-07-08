package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "permiso")
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_permiso;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id_rol")
    private Rol rol;
}
