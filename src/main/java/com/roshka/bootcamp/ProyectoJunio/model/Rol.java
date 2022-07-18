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

    public Rol(String nombre, Set<Permiso> permisos) {
        this.nombre = nombre;
        this.permisos = permisos;
    }

    public Rol(String nombre) {
        this.nombre = nombre;
    }

    public Rol() {

    }

    /* RELACION PARA LA BASE DE DATOS
    *  [fetch = FetchType.EAGER, cascade = CascadeType.ALL] es para
    *  obtener todos los permisos del rol.
    * */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//(mappedBy = "roles")
    @JoinTable(name = "rol_permiso",
            joinColumns = {@JoinColumn(name = "rol_id")},
            inverseJoinColumns = {@JoinColumn(name = "permiso_id")})
    //private Set<Permiso> permisos = new HashSet<>();
    private Set<Permiso> permisos = new HashSet<>();

}
