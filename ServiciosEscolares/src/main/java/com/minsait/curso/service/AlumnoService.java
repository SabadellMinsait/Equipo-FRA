package com.minsait.curso.service;

import java.util.List;

import com.minsait.curso.model.entity.Alumno;

/**
 * Interfaz para interactuar con el repositorio de Alumno
 * @author fvelez
 * @version 1.0
 */
public interface AlumnoService {
	/**
	 * Funci&#243;n para recuperar la lista completa de alumnos registrados 
	 * @return Regresa un List de la clase Alumnos
	 */
	public List<Alumno> findAll();
	
}
