package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

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
	
	/**
	 * Funci&#243;n para recuperar el detalle del alumno por numero de cuenta
	 * @param numCuenta: Numero de cuenta del alumno
	 * @return Registro del alumno
	 */
	public Optional<Alumno> findById(Long numCuenta);
	
	/**
	 * Funci&#243;n para guardar un nuevo alumno 
	 * @param alumno: Registro del alumno
	 * @return Registro del alumno guardado
	 */
	public Alumno create(Alumno alumno);
	
	/**
	 * Funci&#243;n para guardar un alumno 
	 * @param alumno: Registro del alumno
	 * @return Registro del alumno guardado
	 */
	public Alumno save(Long numCuenta, Alumno alumno);
	
	/**
	 * Funci&#243;n para borrar un alumno
	 * @param numCuenta Numero de cuenta del alumno
	 */
	public void delete(Long numCuenta);
}
