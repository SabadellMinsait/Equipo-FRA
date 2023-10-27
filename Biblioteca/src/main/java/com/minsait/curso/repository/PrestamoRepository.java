package com.minsait.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.curso.model.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{
	
	

}
