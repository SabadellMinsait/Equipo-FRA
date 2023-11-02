package com.minsait.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.curso.model.entity.Profesor;

/**
 * Interfaz para generar las instrucciones dinamicas por JPA a la tabla de profesor
 * @author Alejandro Vences
 * @version 1.0
 */
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

}
