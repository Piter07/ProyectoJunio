package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.Permiso;
import com.roshka.bootcamp.ProyectoJunio.model.Rol;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.PermisoRepository;
import com.roshka.bootcamp.ProyectoJunio.controller.repository.RolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    public List<Rol> list() {
        return rolRepository.findAll();
    }

    public Optional<Rol> findById(Long id) {
        return rolRepository.findById(id);
    }

    public Rol getDefaultRol() {

        String nombre = "admin";
        Rol rol = rolRepository.findByNombre(nombre);

        /* si el rol por defecto no existe se crea */
        if(rol == null) {
            /* crear roles y permisos para 'user' y 'admin' */
            Permiso per1 = new Permiso("conectarse");
            Permiso per2 = new Permiso("comentar");
            Permiso per3 = new Permiso("ver_foto");
            Permiso per4 = new Permiso("crear_album");

            per1 = permisoRepository.save(per1);
            per2 = permisoRepository.save(per2);
            per3 = permisoRepository.save(per3);
            per4 = permisoRepository.save(per4);

            Rol user = new Rol("user");
            Rol admin = new Rol("admin");

            user.getPermisos().add(per1);
            user.getPermisos().add(per2);
            user.getPermisos().add(per3);

            admin.getPermisos().add(per1);
            admin.getPermisos().add(per2);
            admin.getPermisos().add(per3);
            admin.getPermisos().add(per4);

            rolRepository.save(admin);
            rolRepository.save(user);

            //rolRepository.flush();

            return user;
        }

        return rol;

    }

}
