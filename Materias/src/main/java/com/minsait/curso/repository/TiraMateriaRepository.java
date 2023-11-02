package com.minsait.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.curso.model.entity.TiraMaterias;

/**
 * Interfaz para generar las instrucciones dinamicas por JPA a la tabla de TiraMateria
 * @author Alejandro Vences
 * @version 1.0
 */

public interface TiraMateriaRepository extends JpaRepository<TiraMaterias, Long> { 

}
