package com.minsait.curso.controller;

import java.util.List;
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

import com.minsait.curso.model.entity.Materia;
import com.minsait.curso.service.MateriaService;

/**
 * Representaci&#243;n del controlador principal para las materias
 * @author Alejandro Vences
 * @version 1.0
 */
@RestController
@RequestMapping("/api/materias")
public class MateriasController {
	
	
	/**
	 * Servicio para acceder al repositorio de materia
	 */
	@Autowired
	MateriaService service; 
	
	/**
	 * End point de bienvenida
	 * @return Regresa un saludo a la API de materias 
	 */
	@GetMapping("/bienvenida")
	public ResponseEntity<String> Bienvenida() {	
		return new ResponseEntity<String>("Bienvenido a materias", HttpStatus.OK);
	}
	
	/**
	 * Funci&#243;n para obtener la lista de las materias registradas 
	 * @return Lista de materias
	 */
	@GetMapping()
	public List<Materia> findAll() {	
		return service.findAll();
	}

	/**
	 * Funci&#243;n para recuperar la materia por id
	 * @param idMateria: Id de la materia
	 * @return Registro de la materia
	 */
	@GetMapping("/{idMateria}")
	public ResponseEntity<Materia> findById(@PathVariable Long idMateria){
		Optional<Materia> materia = service.findById(idMateria);
		if (!materia.isPresent())
			return ResponseEntity.notFound().build();
		return new ResponseEntity<Materia>(materia.get(), HttpStatus.OK);
	}

	/**
	 * Funci&#243;n para guardar una nueva materia
	 * @param materia: Registro de la materia
	 * @return Registro de la materia guardada
	 */
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public ResponseEntity<String> create(
    		@RequestBody Materia materia){
		try {
			// Creamos la materia
			Materia materiaCreada = service.create(materia);
			// Armamos la respuesta 
			String respuesta = materiaCreada.getIdMateria() + ": " + materiaCreada.getNombre();
			// Se llama el método para crear el registro del repositorio JPA
			
		return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Funci&#243;n para guardar una nueva materia
	 * @param idMateria: identificador de la materia
	 * @param materia : Registro de la materia
	 * @return Registro de la materia guardada
	 */
	@PutMapping("/{idMateria}")
	@Transactional
	public ResponseEntity<String> update(
    		@PathVariable Long idMateria,
			@RequestBody Materia materia){
		try {
			// Se valida si cuenta ya con Id
			Optional<Materia> materiaActual = service.findById(idMateria);
			// En caso de que no se encontro la materia se regresa un not found
			if (!materiaActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el método para guardar el registro del repositorio JPA
			Materia materiaCreada = service.create(materia);
			// Armamos la respuesta 
			String respuesta = materiaCreada.getIdMateria() + ": " + materiaCreada.getNombre();
			return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Funci&#243;n para guardar una nueva materia
	 * @param idMateria: Registro de la materia
	 * @return Registro de la materia guardada
	 */
	@DeleteMapping("/delete/{idMateria}")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
    public ResponseEntity<?> delete(@PathVariable Long idMateria) {
		try {
			// Se valida si cuenta ya con Id
			Optional<Materia> materiaActual = service.findById(idMateria);
			// En caso de que no se encontro la materia se regresa un not found
			if (!materiaActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el borrado del repositorio JPA
			service.delete(idMateria);
			// Regresamos el resultado como correcto
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
