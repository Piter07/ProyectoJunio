package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.ReaccionFoto;
import com.roshka.bootcamp.ProyectoJunio.repository.ReaccionFotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaccionFotoService {
    @Autowired
    private ReaccionFotoRepository reaccionFotoRepository;

    public List<ReaccionFoto> list() {
        return reaccionFotoRepository.findAll();
    }

    public Optional<ReaccionFoto> findById(Long id) {
        return reaccionFotoRepository.findById(id);
    }
}
