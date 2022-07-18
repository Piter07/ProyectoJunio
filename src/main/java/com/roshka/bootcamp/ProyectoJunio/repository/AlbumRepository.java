package com.roshka.bootcamp.ProyectoJunio.repository;

import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.Optional;

@Repository
public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
    Page<Album> findByCategoria(Optional<Categoria> categoria, Pageable pageable);

}
