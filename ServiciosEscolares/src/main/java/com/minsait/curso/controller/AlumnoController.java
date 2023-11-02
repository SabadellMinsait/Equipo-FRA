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

import com.minsait.curso.model.entity.Alumno;
import com.minsait.curso.service.AlumnoService;

/**
 * Representaci&#243;n del controlador principal para los alumnos
 * @author fvelez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/servicios/escolares/alumnos")
public class AlumnoController {
	
	/**
	 * Servicio para acceder al repositorio de Alumno
	 */
	@Autowired
	AlumnoService service;
	
	/**
	 * End point de bienvenida
	 * @return Regresa un saludo a la API de alumnos 
	 */
	@GetMapping("/bienvenida")
	public ResponseEntity<String> Bienvenida() {	
		return new ResponseEntity<String>("Bienvenido a alumnos", HttpStatus.OK);
	}
	
	/**
	 * Funci&#243;n para obtener la lista de alumnos registrados 
	 * @return Lista de alumnos
	 */
	@GetMapping()
	public ResponseEntity<String> findAll() {	
		return new ResponseEntity<String>("callbackfn(" + service.findAll().toString() + ")", HttpStatus.OK);
	}

	/**
	 * Funci&#243;n para recuperar el alumno por n&#250;mero de cuenta
	 * @param numCuenta N&#250;mero de cuenta del alumno
	 * @return Registro del alumno
	 */
	@GetMapping("/{numCuenta}")
	public ResponseEntity<Alumno> findById(@PathVariable Long numCuenta){
		Optional<Alumno> alumno = service.findById(numCuenta);
		if (!alumno.isPresent())
			return ResponseEntity.notFound().build();
		return new ResponseEntity<Alumno>(alumno.get(), HttpStatus.OK);
	}

	/**
	 * Funci&#243;n para guardar un nuevo alumno 
	 * @param alumno: Registro del alumno
	 * @return Registro del alumno guardado
	 */
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public ResponseEntity<String> create(
    		@RequestBody Alumno alumno){
		try {
			// Creamos el alumno
			Alumno alumnoCreado = service.create(alumno);
			// Armamos la respuesta 
			String respuesta = alumnoCreado.getNumCuenta() + ": " + alumnoCreado.getNombre();
			// Se llama el método para crear el registro del repositorio JPA
			
		return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Funci&#243;n para guardar un nuevo alumno 
	 * @param numCuenta: N&#250;mero de cuenta del alumno
	 * @param alumno: Registro del alumno
	 * @return Registro del alumno guardado
	 */
	@PutMapping("/{numCuenta}")
	@Transactional
	public ResponseEntity<String> update(
    		@PathVariable Long numCuenta,
			@RequestBody Alumno alumno){
		try {
			// Se valida si cuenta ya con numero de cuenta
			Optional<Alumno> alumnoActual = service.findById(numCuenta);
			// En caso de que no se encontro el alumno se regresa un not found
			if (!alumnoActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el método para guardar el registro del repositorio JPA
			Alumno alumnoCreado = service.create(alumno);
			// Armamos la respuesta 
			String respuesta = alumnoCreado.getNumCuenta() + ": " + alumnoCreado.getNombre();
			return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Funci&#243;n para borrar el registro del  alumno 
	 * @param numCuenta: N&#250;mero de cuenta del alumno
	 * @return Respuesta correcta o Excepci&#243;n encontrada
	 */
	@DeleteMapping("/delete/{numCuenta}")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
    public ResponseEntity<?> delete(@PathVariable Long numCuenta) {
		try {
			// Se valida si cuenta ya con numero de cuenta
			Optional<Alumno> alumnoActual = service.findById(numCuenta);
			// En caso de que no se encontro el alumno se regresa un not found
			if (!alumnoActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el borrado del repositorio JPA
			service.delete(numCuenta);
			// Regresamos el resultado como correcto
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Creaci&#243;n de un controlador de alumno vac&#237;o 
	 */
	public AlumnoController() {
		
	}
}
