package com.roshka.bootcamp.ProyectoJunio.service;

import com.roshka.bootcamp.ProyectoJunio.controller.dto.AlbumDTO;
import com.roshka.bootcamp.ProyectoJunio.model.Album;
import com.roshka.bootcamp.ProyectoJunio.model.Categoria;
import com.roshka.bootcamp.ProyectoJunio.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.*;

@Service
public class AlbumService implements IAlbumService,AlbumServiceInterface  {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private CategoriaService  categoriaService;

    public List<Album> list() {
        return (List<Album>) albumRepository.findAll();
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
        //buscar categoria por medio de categoriaService.findById(id_categoria)
        Long id_categoria=albumDTO.getIdCategoria();
        System.out.println("\n\n\n\n\n\n\n\n" + id_categoria);
        Optional<Categoria> cat=categoriaService.findById(id_categoria);
//        Optional categoriaAuxiliar=categoriaService.findById(id_categoria);
        album.setCategoria(cat.get());
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

    public Page<Album> findPaginated(int pageNo, int pageSize) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("fechaCreacion").descending());
        Page<Album> pagedResult = albumRepository.findAll(paging);

        return pagedResult;
    }

    public String[] findPages(int size){
        String[] pages =  new String[size];
        for (int i = 0 ; i < pages.length ; i++){
            pages[i] = String.valueOf(i);

        }
        return pages;
    }


    @Override
    public Page<Album> findPaginatedFilter(int pageNo, int pageSize, Optional<Categoria> categ) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Album> pagedResult = albumRepository.findByCategoria(categ, paging);

        return pagedResult;
    }


}
