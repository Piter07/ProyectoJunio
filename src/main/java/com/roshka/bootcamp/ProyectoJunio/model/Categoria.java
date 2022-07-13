package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_categoria;

    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private Set<Album> albumes;

}
