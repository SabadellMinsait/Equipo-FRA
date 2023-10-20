package com.minsait.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.curso.model.entity.Alumno;

/**
 * Interfaz para generar las instrucciones dinamicas por JPA a la tabla de alumno
 * @author fvelez
 * @version 1.0
 */
public interface AlumnoRepository extends JpaRepository<Alumno, Long>{

}
