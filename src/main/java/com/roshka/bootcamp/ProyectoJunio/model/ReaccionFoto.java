package com.roshka.bootcamp.ProyectoJunio.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class ReaccionFoto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_reaccion_foto;

    @ManyToOne
    @JoinColumn(name = "foto_id", referencedColumnName = "id_foto")
    private Foto foto;

    @ManyToOne
    @JoinColumn(name = "reaccion_id", referencedColumnName = "id_reaccion")
    private Reaccion reaccion;

    @OneToOne
    private Usuario usuario;
}
