package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name="estado")
    private String estado;

    @Column(name = "correo")
    private String correo;

    @Column(name = "clave")
    private String clave;

    @OneToMany(mappedBy = "usuario")
    private Set<Rol> roles = new HashSet<>();

}
