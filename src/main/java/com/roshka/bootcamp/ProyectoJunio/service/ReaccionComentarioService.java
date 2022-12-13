package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.ReaccionComentario;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.ReaccionComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaccionComentarioService {
    @Autowired
    private ReaccionComentarioRepository reaccionComentarioRepository;

    public List<ReaccionComentario> list() {
        return reaccionComentarioRepository.findAll();
    }

    public Optional<ReaccionComentario> findById(Long id) {
        return reaccionComentarioRepository.findById(id);
    }
}
