package com.minsait.curso.controller;
 
import java.util.Optional;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.curso.model.entity.Status;
import com.minsait.curso.service.StatusService;

/**
 * Representaci&#243;n del controlador principal de Biblioteca
 * @author fvelez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaController {

	/**
	 * Propiedad para acceder al servicio del servicio de estatus
	 */
	@Autowired
	StatusService statusService;
	
	/**
	 * End point de bienvenida
	 * @return Mensaje de bienvenida a la API de Biblioteca
	 */
	@GetMapping("/bienvenida")
	public ResponseEntity<String> Bienvenida() {	
		return new ResponseEntity<String>("Bienvenido a la Biblioteca", HttpStatus.OK);
	}

	/**
	 * Funci&#243;n para recuperar el estatus por n&#250;mero de cuenta
	 * @param numCuenta: N&#250;mero de cuenta del alumno
	 * @return Registro del status del alumno en la biblioteca
	 */
	@GetMapping("/status/{numCuenta}")
	public ResponseEntity<Status> findByNumcuenta(@PathVariable Long numCuenta){
		Optional<Status> status = statusService.findByNumCuenta(numCuenta);
		if (!status.isPresent())
			return ResponseEntity.notFound().build();
		return new ResponseEntity<Status>(status .get(), HttpStatus.OK);
	}

	/**
	 * Creaci&#243;n de ua aplicaci&#243;n de controlador de biblioteca vac&#237;o
	 */
	public BibliotecaController() {
		
	}
	
}
