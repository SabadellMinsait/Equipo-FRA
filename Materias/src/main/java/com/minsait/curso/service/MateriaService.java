package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import com.minsait.curso.model.entity.Materia;

/**
 * Interfaz para interactuar con el repositorio de Materia
 * @author Alejandro Vences
 * @version 1.0
 */

public interface MateriaService {
	
	/**
	 * Funci&#243;n para recuperar la lista completa de materias registradas 
	 * @return Regresa un List de la clase Materia
	 */
	public List<Materia> findAll();
	
	/**
	 * Funci&#243;n para recuperar el detalle de la materia por identificador
	 * @param idMateria: Identificador de la materia
	 * @return Registro del alumno
	 */
	public Optional<Materia> findById(Long idMateria);
	
	
	/**
	 * Funci&#243;n para guardar una nueva materia 
	 * @param materia: Registro de la materia
	 * @return Registro de la materia creada
	 */
	public Materia create(Materia materia);
	
	/**
	 * Funci&#243;n para guardar una materia 
	 * @param idMateria: Identificador de la materia
	 * @param materia: Registro de la materia a guardar
	 * @return Registro de la materia guardada
	 */
	
	public Materia save(Long idMateria, Materia materia);
	
	
	/**
	 * Funci&#243;n para borrar una materia
	 * @param idMateria Identificador de la materia
	 */
	public void delete(Long idMateria);
	
}
