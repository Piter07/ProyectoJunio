package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Foto;
import com.roshka.bootcamp.ProyectoJunio.repository.FotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FotoService {
    @Autowired
    private FotoRepository fotoRepository;

    public List<Foto> getFotos(Long idAlbum) {
        Album album = new Album();
        album.setId_album(idAlbum);
        return fotoRepository.findAllByAlbum(album);
    }

    public Optional<Foto> findById(Long id) {
        return fotoRepository.findById(id);
    }
}
