package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "correo"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

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
    @OneToOne(mappedBy = "usuario")
    private ReaccionFoto reaccionFoto;

    /* Relaciona que a un usuario le pertenece una reaccion de un comentario */
    @OneToOne(mappedBy = "usuario")
    private ReaccionComentario reaccionComentario;

    /* Un usuario puede tener varios comentarios en una foto */
    @OneToMany(mappedBy = "usuario")
    private Set<Comentario> comentario = new HashSet<>();

    /* Un usuario puede tener varios albums */

    @OneToMany(mappedBy = "usuario")
    private Set<Album> albums = new HashSet<>();
}
