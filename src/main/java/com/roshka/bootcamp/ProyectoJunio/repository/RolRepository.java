package com.roshka.bootcamp.ProyectoJunio.repository;

import com.roshka.bootcamp.ProyectoJunio.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Usuario, Long>{

	public Usuario findByEmail(String email);
	
}