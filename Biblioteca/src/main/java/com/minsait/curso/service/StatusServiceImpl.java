package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.minsait.curso.model.entity.Prestamo;
import com.minsait.curso.model.entity.Status;


/**
 * Interfaz para interactuar con el repositorio del status Alumno
 * @author fvelez
 * @version 1.0
 */
@Service
public class StatusServiceImpl implements StatusService {
	
	/**
	 * Servicio de prestamos
	 */
	@Autowired
	PrestamoService prestamoService;
	
	/**
	 * Funci&#243;n para recuperar el detalle del alumno por numero de cuenta
	 * @param numCuenta: : N&#250;mero de cuenta del alumno
	 * @return Regresa el estatus del alumno en biblioteca
	 */
	@Override
	public Optional<Status> findByNumCuenta(Long numCuenta) {
		
		Assert.isTrue(numCuenta != null, "El numero de cuenta no puede ser nulo");
		
		// Creamos la estructura de estatus
		Status status = new Status();
		status.setNumCuenta(numCuenta);
		// Obtenemos los prestamos asociados al alumno
		List<Prestamo> prestamos = prestamoService.findByNumCuenta(numCuenta);
		// Validamos si tiene algun prestamo
		if (prestamos!= null &&!prestamos.isEmpty()) {
			// Buscamos prestamos con fecha de entrega en nulo
			List<Prestamo> prestamosPendientes = prestamos.stream().filter(c->c.getFechaEntrega() == null).collect(Collectors.toList());
			if (prestamosPendientes != null &&!prestamosPendientes.isEmpty()) {
				status.setStatus("Con adeudo");
			}else {
				status.setStatus("Sin adeudo");
			}
			
		}else {
			status.setStatus("Sin adeudo");	
		}
		
		Optional<Status> optional = Optional.of(status);
		
		return optional;
	}

	/**
	 * Creaci&#243;n de un sercvicios de status vac&#237;o
	 */
	public StatusServiceImpl() {
		
	}

}
