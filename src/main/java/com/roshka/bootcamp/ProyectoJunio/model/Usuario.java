package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
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

    /* Relaciona que a un usuario le pertenece una reaccion de una foto */
    @OneToOne
    private ReaccionFoto reaccionFoto;

    /* Relaciona que a un usuario le pertenece una reaccion de un comentario */
    @OneToOne
    private ReaccionComentario reaccionComentario;

    /* un comentario pertenece a un usuario */
    @OneToOne
    private Comentario comentario;

}
