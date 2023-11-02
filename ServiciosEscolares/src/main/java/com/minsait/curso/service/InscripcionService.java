package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import com.minsait.curso.model.entity.Inscripcion;

/**
 * Interfaz para interactuar con el repositorio de Inscripci&#243;n
 * @author fvelez
 * @version 1.0
 */
public interface InscripcionService {
	
	/**
	 * Funci&#243;n para recuperar la lista completa de inscrpciones registrados 
	 * @return Regresa un List de la clase Inscrpcion
	 */
	public List<Inscripcion> findAll();
	
	/**
	 * Funci&#243;n para recuperar la inscrpciones registrados
	 * @param idPeriodo: Identificador del periodo
	 * @return Registro de la inscripcii&#243;n
	 */
	public Optional<Inscripcion> findById(Long idPeriodo);
	
	/**
	 * Funci&#243;n para recuperar la &#250;ltima inscrpciones de un alumno
	 * @param numCuenta: n&#250;mero del alumno 
	 * @return Registro de la &#250;ltima inscripci&#243;n
	 */
	public Optional<Inscripcion> findByNumCuenta(Long numCuenta);
	
	/**
	 * Funci&#243;n para registrar la inscrpci&#243;n de un alumno
	 * @param inscripcion: Registro de la inscripci&#243;n a crear
	 * @return Registro de la inscripci&#243;n
	 */
	public Inscripcion create(Inscripcion inscripcion);
	
	/**
	 * Funci&#243;n para actualizar la inscrpci&#243;n de un alumno
	 * @param idPerdiodo: Identificador del periodo
	 * @param inscripcion: Registro de la inscripci&#243;n a actualizar
	 * @return Registro de la inscripci&#243;n
	 */
	public Inscripcion save(Long idPerdiodo, Inscripcion inscripcion);
	

}
