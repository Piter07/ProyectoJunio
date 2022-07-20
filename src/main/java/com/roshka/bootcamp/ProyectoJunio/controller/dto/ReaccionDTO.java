package com.roshka.bootcamp.ProyectoJunio.controller.dto;

import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.model.Reaccion;
import com.roshka.bootcamp.ProyectoJunio.model.Usuario;
import lombok.Data;

@Data
public class ReaccionDTO {
    String reaccion;
    String foto;
    String pageAnt;
}
