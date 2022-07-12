package com.roshka.bootcamp.ProyectoJunio.model;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
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

    private String email;

    private String password;

    @Column(name = "token_verificacion")
    private String tokenVerificacion;

    @OneToMany(mappedBy = "usuario")
    private Set<Rol> roles = new HashSet<>();


    public Usuario() {

    }

    public Usuario(String nombre, String apellido, String email, String password, Collection<Rol> roles) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    /** RELACIONES PARA LA BASE DE DATOS **/
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "usuarios")
    //private Set<Rol> roles = new HashSet<>();
    private Collection<Rol> roles;

    /*
    @ManyToMany//(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rol",
                joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id_usuario"),
                inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id_rol")
    )
    private Collection<Rol> roles;

     */

    /* Relaciona que a un usuario le pertenece una reaccion de una foto
    @OneToOne(mappedBy = "usuario")
    private ReaccionFoto reaccionFoto;

    // Relaciona que a un usuario le pertenece una reaccion de un comentario
    @OneToOne(mappedBy = "usuario")
    private ReaccionComentario reaccionComentario;

    // Un usuario puede tener varios comentarios en una foto
    @OneToMany(mappedBy = "usuario")
    private Set<Comentario> comentario = new HashSet<>();

    // Un usuario puede tener varios albums
    @OneToMany(mappedBy = "usuario")
    private Set<Album> albums = new HashSet<>();
    */


}
