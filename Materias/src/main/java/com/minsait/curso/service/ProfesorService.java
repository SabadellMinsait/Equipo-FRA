package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import com.minsait.curso.model.entity.Profesor;

/**
 * Interfaz para interactuar con el repositorio de Profesor
 * @author Alejandro Vences
 * @version 1.0
 */

public interface ProfesorService {
	
	/**
	 * Funcion para recuperar la lista completa de profesores registrados 
	 * @return Regresa un List de la clase Profesor
	 */
	public List<Profesor> findAll();
	
	
	/**
	 * Funci&#243;n para recuperar el detalle del profesor por identificador
	 * @param idProfesor: identificador del profesor
	 * @return Registro del profesor
	 */
	public Optional<Profesor> findById(Long idProfesor) ;
	
	
	/**
	 * Funci&#243;n para guardar un nuevo profesor 
	 * @param idProfesor: Registro del profesor
	 * @return Registro del profesor guardado
	 */
	public Profesor create(Profesor idProfesor);
	
	
	/**
	 * Funci&#243;n para guardar un profesor 
	 * @param idProfesor: identificador del profesor
	 * @param profesor: Registro del profesor a guardar
	 * @return Registro del alumno guardado
	 */
	public Profesor save(Long idProfesor, Profesor profesor);
	
	/**
	 * Funci&#243;n para borrar un profesor
	 * @param idProfesor Identificador del profesor
	 */
	
	public void delete(Long idProfesor);
	
	
}
