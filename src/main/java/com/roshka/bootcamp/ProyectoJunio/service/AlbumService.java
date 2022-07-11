package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AlbumService implements AlbumServiceInterface {
    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> list() {
        return albumRepository.findAll();
    }

    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public Album guardar(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setTitulo(albumDTO.getTitulo());
        album.setDescripcion(albumDTO.getDescripcion());
        album.setFechaEvento(new Date());
        album.setId_categoria(albumDTO.getIdCategoria());
//        album.setUsuario();
        return albumRepository.save(album);
    }
    
}
