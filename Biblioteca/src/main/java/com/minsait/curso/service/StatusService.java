package com.minsait.curso.service;

import java.util.Optional;

import com.minsait.curso.model.entity.Status;

/**
 * Interfaz para interactuar con el repositorio de Status
 * @author fvelez
 * @version 1.0
 */
public interface StatusService {

	/**
	 * Funci&#243;n para recuperar el detalle del alumno por numero de cuenta
	 * @param numCuenta: : N&#250;mero de cuenta del alumno
	 * @return Regresa el estatus del alumno en biblioteca
	 */
	public Optional<Status> findByNumCuenta(Long numCuenta);
	
	
}
