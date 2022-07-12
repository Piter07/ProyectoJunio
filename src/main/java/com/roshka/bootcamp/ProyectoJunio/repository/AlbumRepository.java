package com.roshka.bootcamp.ProyectoJunio.repository;

import com.roshka.bootcamp.ProyectoJunio.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends PagingAndSortingRepository<Album, Long> {
}
