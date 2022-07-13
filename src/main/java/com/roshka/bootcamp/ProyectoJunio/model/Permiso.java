package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "permiso")
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_permiso;
    private String nombre;

    public Permiso(String nombre) {
        this.nombre = nombre;
    }

    public Permiso() {

    }

    @ManyToMany
    @JoinTable(name = "rol_permiso",
            joinColumns = @JoinColumn(name = "permiso_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    //private Set<Rol> roles = new HashSet<>();
    private Collection<Rol> roles;

}
