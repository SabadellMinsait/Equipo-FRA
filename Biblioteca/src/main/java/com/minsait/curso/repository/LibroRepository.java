package com.minsait.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.curso.model.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{
	
	

}
