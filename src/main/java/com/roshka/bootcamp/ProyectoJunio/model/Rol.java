package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario")
    private Usuario usuario;

    @ManyToMany(mappedBy = "roles")
    private Set<Permiso> permisos = new HashSet<>();
}
