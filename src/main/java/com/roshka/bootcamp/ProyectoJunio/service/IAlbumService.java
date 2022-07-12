package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.Album;

import java.util.List;

public interface IAlbumService  {
    List<Album> findPaginated(int pageNo, int pageSize);
}