package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        Date date_auxiliar;
        album.setTitulo(albumDTO.getTitulo());
        album.setDescripcion(albumDTO.getDescripcion());
        album.setId_categoria(albumDTO.getIdCategoria());
        album.setFechaCreacion(new Date());
        try {
            date_auxiliar=new SimpleDateFormat("dd/MM/yyyy").parse(albumDTO.getFechaEvento());
        } catch (ParseException e) {
            date_auxiliar=new Date();
        }
        album.setFechaEvento(date_auxiliar);
        album.setUsuario(albumDTO.getUsuario());
        return albumRepository.save(album);
    }
    
}
