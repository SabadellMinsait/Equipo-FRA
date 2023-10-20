package com.minsait.curso.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.curso.model.entity.Inscripcion;
import com.minsait.curso.service.InscripcionService;

/**
 * Representaci&#243;n del controlador principal de Servicios Escolares
 * @author fvelez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/servicios/escolares")
public class ServiciosEscolaresController {
	
	@Autowired
	InscripcionService service;
	
	/**
	 * End point de bienvenida
	 * @return 
	 */
	@GetMapping("/bienvenida")
	public ResponseEntity<String> Bienvenida() {	
		return new ResponseEntity<String>("Bienvenido a servicios escolares", HttpStatus.OK);
	}
	
	/**
	 * Funci&#243;n para obtener la lista de inscripciones registradas
	 * @return Lista de inscripciones
	 */
	@GetMapping
	public List<Inscripcion> findAll(){
		// Obtenenmos la lista del servicio de inscripciones
		return service.findAll();
	}
	
	/**
	 * Funci&#243;n para recuperar la inscrpci&#243;n por id periodo
	 * @param Identificador del periodo
	 * @return Registro de la inscripcii&#243;n
	 */
	@GetMapping("/{idPeriodo}")
	public ResponseEntity<?> findById(@PathVariable Long idPeriodo){
		Optional<Inscripcion> inscripcion = service.findById(idPeriodo);
		if (!inscripcion.isPresent())
			return ResponseEntity.notFound().build();
		return new ResponseEntity<Inscripcion>(inscripcion .get(), HttpStatus.OK);
	}

	/**
	 * Funci&#243;n para recuperar la inscrpci&#243;n por id periodo
	 * @param Identificador del periodo
	 * @return Registro de la inscripcii&#243;n
	 */
	@GetMapping("/byNumCuenta/{numCuenta}")
	public ResponseEntity<?> findByNumcuenta(@PathVariable Long numCuenta){
		Optional<Inscripcion> inscripcion = service.findByNumCuenta(numCuenta);
		if (!inscripcion.isPresent())
			return ResponseEntity.notFound().build();
		return new ResponseEntity<Inscripcion>(inscripcion .get(), HttpStatus.OK);
	}

	/**
	 * Funci&#243;n para registrar la inscripci#243;n 
	 * @param inscripcion: Registro de inscripci#243;n
	 * @return Registro de la inscripci#243;n
	 */
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public ResponseEntity<?> create(
    		@RequestBody Inscripcion inscripcion){
		try {
			// Se llama el método para crear el registro del repositorio JPA
			return new ResponseEntity<Inscripcion>(service.create(inscripcion), HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Funci&#243;n para guardar una inscripci&#243;n 
	 * @param idPeriodo: Identificador del periodo
	 * @param inscripcion: Registro de la inscripci&#243;n 
	 * @return Registro de la inscripci&#243;n
	 */
	@PutMapping("/{idPeriodo}")
	@Transactional
	public ResponseEntity<?> update(
    		@PathVariable Long idPeriodo,
			@RequestBody Inscripcion inscripcion){
		try {
			// Se valida si cuenta ya con numero de cuenta
			Optional<Inscripcion> inscripcionActual = service.findById(idPeriodo);
			// En caso de que no se encontro el alumno se regresa un not found
			if (!inscripcionActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el método para guardar el registro del repositorio JPA
			return new ResponseEntity<Inscripcion>(service.save(idPeriodo, inscripcion), HttpStatus.OK);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
