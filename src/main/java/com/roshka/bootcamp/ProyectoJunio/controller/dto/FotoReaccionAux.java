package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Reaccion;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class FotoReaccionAux {
    /* Utilizado en la vista foto-comentario.html para conocer
     * a los usuarios de una reaccion para una foto.
     * */
    private Reaccion reaccion;
    private Set<Usuario> usuario = new HashSet<>();
    private Foto foto;
}
