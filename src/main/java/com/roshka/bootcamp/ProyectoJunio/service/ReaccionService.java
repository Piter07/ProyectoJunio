package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.Reaccion;
import com.roshka.bootcamp.ProyectoJunio.repository.ReaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaccionService {
    @Autowired
    private ReaccionRepository reaccionRepository;

    public List<Reaccion> list() {
        return reaccionRepository.findAll();
    }

    public Optional<Reaccion> findById(Long id) {
        return reaccionRepository.findById(id);
    }
}
