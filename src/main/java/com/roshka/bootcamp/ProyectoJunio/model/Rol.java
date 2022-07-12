package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;
    private String nombre;

    public Rol(String nombre, List<Permiso> permisos) {
        this.nombre = nombre;
        this.permisos = permisos;
    }

    public Rol(String nombre) {
        this.nombre = nombre;
    }

    public Rol() {

    }


    // forma 1: agregando las anotaciones a ambas entidades

    @ManyToMany
    @JoinTable(name = "usuario_rol",
                joinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id_rol"),
                inverseJoinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario"))
    //private Set<Usuario> usuarios = new HashSet<>();
    private Collection<Usuario> usuarios;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    //private Set<Permiso> permisos = new HashSet<>();
    private Collection<Permiso> permisos;

    /*
    //forma 2: agregando las anotaciones sola a la tabla origen
     */

    /*
    @ManyToMany//(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "rol_permiso",
                joinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id_rol"),
                inverseJoinColumns = @JoinColumn(name = "permiso_id", referencedColumnName = "id_permiso")
    )
    private Collection<Permiso> permisos;
     */



}
