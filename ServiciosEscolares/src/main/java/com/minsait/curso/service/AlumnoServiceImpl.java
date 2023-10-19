package com.minsait.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.curso.model.entity.Alumno;
import com.minsait.curso.repository.AlumnoRepository;

/**
 * Implementaci&#243;n de AlumnoService para interactuar con el repositorio de Alumno
 * @author fvelez
 * @version 1.0
 */
@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	/**
	 * Propiedad repositorio de tipo AlumnoRepository
	 */
	@Autowired
	private AlumnoRepository repository;
	
	/**
	 * Funci&#243;n para recuperar la lista completa de alumnos registrados 
	 * @return Regresa un List de la clase Alumnos
	 */
	@Override
	public List<Alumno> findAll() {
		// Regresamos la lista genetada por la implementación a JPA
		return repository.findAll();
	}

}
