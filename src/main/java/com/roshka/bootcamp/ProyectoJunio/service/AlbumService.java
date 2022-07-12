package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.*;

@Service
public class AlbumService implements IAlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public List<Album> list() {
        return (List<Album>) albumRepository.findAll();
    }

    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id);
    }


    public List<Album> findPaginated(int pageNo, int pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Album> pagedResult = albumRepository.findAll(paging);

        return pagedResult.toList();
    }

    public String[] findPages(){
        int size = (int) Math.ceil((this.list().size())/((double)(3)));
        String[] pages =  new String[size];
        for (int i = 0 ; i < pages.length ; i++){
            pages[i] = String.valueOf(i);
        }
        return pages;
    }



}
