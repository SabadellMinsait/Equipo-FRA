package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import com.minsait.curso.model.entity.TiraMaterias;



/**
 * Interfaz para interactuar con el repositorio de una tiraMateria
 * @author Alejandro Vences
 * @version 1.0
 */
public interface TiraMateriasService {
	
	/**
	 * Funcion para recuperar la lista completa de materias registrados 
	 * @return Regresa un List de la clase Materia
	 */
	public List<TiraMaterias> findAll();
	
	/**
	 * Funcion para recuperar la lista completa de alumnos registrados 
	 * @param tiraMaterias: Registro de la tira de materia
	 * @return Regresa un List de la clase Materia
	 */
	public TiraMaterias validaMateria(TiraMaterias tiraMaterias);

	/**
	 * Funci&#243;n para guardar una nueva tira de materia 
	 * @param tiraMaterias: Registro de la tira de materia
	 * @return Registro de una tira de materia guardada
	 */
	public TiraMaterias create(TiraMaterias tiraMaterias);
	
	
	/**
	 * Funci&#243;n para recuperar el detalle de una tira de materia por identificador
	 * @param idInsMateria: identificador de la tira de materia
	 * @return Registro del alumno
	 */
	public Optional<TiraMaterias> findById(Long idInsMateria);
	
	
	/**
	 * Funci&#243;n para guardar un alumno 
	 * @param IdInsMateria: identificador de la tira de materia
	 * @param tiraMaterias: Registro de una tira de materia
	 * @return Registro de una tira de materia guardada
	 */
	public TiraMaterias save(Long IdInsMateria, TiraMaterias tiraMaterias);
}
