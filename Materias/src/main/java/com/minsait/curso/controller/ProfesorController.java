package com.minsait.curso.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.curso.model.entity.Profesor;
import com.minsait.curso.service.ProfesorService;

/**
 * Representaci&#243;n del controlador principal para los profesores
 * @author Alejandro Vences
 * @version 1.0
 */

@RestController
@RequestMapping("/api/profesor")

public class ProfesorController {
	
	@Autowired
	ProfesorService service;
	
	/**
	 * End point de bienvenida
	 * @return Regresa un saludo a la API de alumnos 
	 */
	@GetMapping("/bienvenida")
	public ResponseEntity<String> Bienvenida() {	
		return new ResponseEntity<String>("Bienvenido a Profesores", HttpStatus.OK);
	}
	
	/**
	 * Funci&#243;n para obtener la lista de los profesores registrados 
	 * @return Lista de profesores
	 */
	@GetMapping()
	public ResponseEntity<String> findAll() {	
		return new ResponseEntity<String>("callbackfn(" + service.findAll().toString() + ")", HttpStatus.OK);
	}

	/**
	 * Funci&#243;n para recuperar el profesor por Id
	 * @param idProfesor identificador del profesor
	 * @return Registro del profesor
	 */
	@GetMapping("/{idProfesor}")
	public ResponseEntity<Profesor> findById(@PathVariable Long idProfesor){
		Optional<Profesor> profesor = service.findById(idProfesor);
		if (!profesor.isPresent())
			return ResponseEntity.notFound().build();
		return new ResponseEntity<Profesor>(profesor.get(), HttpStatus.OK);
	}

	/**
	 * Funci&#243;n para recuperar el profesor por Id
	 * @param profesor: registro del profesor
	 * @return Registro del profesor
	 */
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public ResponseEntity<String> create(
    		@RequestBody Profesor profesor){
		try {
			// Creamos el profesor
			Profesor profesorCreado = service.create(profesor);
			// Armamos la respuesta 
			String respuesta = profesorCreado.getIdProfesor() + ": " + profesorCreado.getNombre();
			// Se llama el método para crear el registro del repositorio JPA
			
		return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Funci&#243;n para recuperar el profesor por Id
	 * @param idProfesor: Identificador del profesor
	 * @param profesor: registro del profesor
	 * @return Registro del profesor
	 */
	@PutMapping("/{idProfesor}")
	@Transactional
	public ResponseEntity<String> update(
    		@PathVariable Long idProfesor,
			@RequestBody Profesor profesor){
		try {
			// Se valida si cuenta ya con Id
			Optional<Profesor> profesorActual = service.findById(idProfesor);
			// En caso de que no se encontro el profesor se regresa un not found
			if (!profesorActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el método para guardar el registro del repositorio JPA
			Profesor profesorCreado = service.save(idProfesor, profesor);
			// Armamos la respuesta 
			String respuesta = profesor.getIdProfesor() + ": " + profesorCreado.getNombre();
			return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Funci&#243;n para recuperar el profesor por Id
	 * @param idProfesor: Identificador del profesor
	 * @return Registro del profesor
	 */
	@DeleteMapping("/delete/{idProfesor}")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
    public ResponseEntity<?> delete(@PathVariable Long idProfesor) {
		try {
			// Se valida si cuenta ya con numero de cuenta
			Optional<Profesor> profesorActual = service.findById(idProfesor);
			// En caso de que no se encontro el profesor se regresa un not found
			if (!profesorActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el borrado del repositorio JPA
			service.delete(idProfesor);
			// Regresamos el resultado como correcto
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
     * Funci&#243;n para generar un registro vacio de ProfesorControler
     */
	public ProfesorController() {
        
    }	
}
