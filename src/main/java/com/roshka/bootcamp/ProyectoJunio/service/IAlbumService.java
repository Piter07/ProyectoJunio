package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Categoria;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IAlbumService  {
    Page<Album> findPaginated(int pageNo, int pageSize);
    String[] findPages(int pageSize);

    Page<Album> findPaginatedFilter(int pageNo, int pageSize, Optional<Categoria> categ);
}