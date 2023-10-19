package com.minsait.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.curso.model.entity.Inscripcion;

/**
 * Interfaz para generar las instrucciones dinamicas por JPA a la tabla de Inscripci&#243;n 
 * @author fvelez
 * @version 1.0
 */
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>{

}
