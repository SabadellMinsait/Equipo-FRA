package com.minsait.curso.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.minsait.curso.model.entity.Status;


/**
 * Interfaz para interactuar con el repositorio del status Alumno
 * @author fvelez
 * @version 1.0
 */
@Service
public class StatusServiceImpl implements StatusService {

	/**
	 * Funci&#243;n para recuperar el detalle del alumno por numero de cuenta
	 * @param numCuenta: : N&#250;mero de cuenta del alumno
	 * @return Regresa el estatus del alumno en biblioteca
	 */
	@Override
	public Optional<Status> findByNumCuenta(Long numCuenta) {
		Status status = new Status();
		status.setNumCuenta(numCuenta);
		status.setStatus("Sin adeudo");
		Optional<Status> optional = Optional.of(status);
		
		return optional;
	}

	/**
	 * Creaci&#243;n de un sercvicios de status vac&#237;o
	 */
	public StatusServiceImpl() {
		
	}

}
