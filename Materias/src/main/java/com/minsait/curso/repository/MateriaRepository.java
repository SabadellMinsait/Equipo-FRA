package com.minsait.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.curso.model.entity.Materia;

/**
 * Interfaz para generar las instrucciones dinamicas por JPA a la tabla de materia
 * @author Alejandro Vences
 * @version 1.0
 */

public interface MateriaRepository extends JpaRepository<Materia, Long> {

} 
