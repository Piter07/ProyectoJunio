package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;

import java.util.List;
import java.util.Optional;


public interface AlbumServiceInterface {

    public List<Album> list();

    public Optional<Album> findById(Long id);

    public Album guardar(AlbumDTO albumDTO);

    public Album editar(Album album);

}
