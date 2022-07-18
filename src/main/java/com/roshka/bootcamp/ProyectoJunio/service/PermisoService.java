package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.Permiso;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermisoService {

    @Autowired
    private PermisoRepository permisoRepository;

    public List<Permiso> list() {
        return permisoRepository.findAll();
    }

    public Optional<Permiso> findById(Long id) {
        return permisoRepository.findById(id);
    }

}
