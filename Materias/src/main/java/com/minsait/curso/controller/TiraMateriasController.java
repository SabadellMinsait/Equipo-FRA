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

import com.minsait.curso.model.entity.TiraMaterias;
import com.minsait.curso.service.TiraMateriasService;

/**
 * Representaci&#243;n del controlador principal para las tiras de materia
 * @author Alejandro Vences
 * @version 1.0
 */

@RestController
@RequestMapping("/api/tiramaterias")
public class TiraMateriasController {

	@Autowired
	TiraMateriasService service; 
	
	/**
	 * End point de bienvenida
	 * @return Regresa un saludo a la API de alumnos 
	 */
	@GetMapping("/bienvenida")
	public String Bienvenida() {	
		return "Bienvenido a materias";
	}
	
	/**
	 * Funci&#243;n para obtener la lista de las tiras de materia 
	 * @return Lista de profesores
	 */
	@GetMapping("")
	public List<TiraMaterias> findAll(){		
		return service.findAll();
	}
	
	/**
	 * Funci&#243;n para guardar una nueva tira de materia 
	 * @param tiraMaterias: Registro de una tira de materia
	 * @return Registro de la tira de materia guardada
	 */
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public ResponseEntity<String> create(
    		@RequestBody TiraMaterias tiraMaterias){
		try {
			// Se llama el método para crear el registro del repositorio JPA
			TiraMaterias inscripcionCreada = service.create(tiraMaterias);
			String respuesta = inscripcionCreada.getIdInsMateria() + ": " + inscripcionCreada.getMateria().getNombre(); 
			return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	/**
	 * Funci&#243;n para guardar una nueva tiraMateria 
	 * @param idInsMateria: identificador de la tira de materia
	 * @param tiraMaterias: Registro de la tira materia
	 * @return Registro del alumno guardado
	 */
	@PutMapping("/{idInsMateria}")
	@Transactional
	public ResponseEntity<String> update(
    		@PathVariable Long idInsMateria,
			@RequestBody TiraMaterias tiraMaterias){
		try {
			// Se valida si existe el identificador de la tira de materia
			Optional<TiraMaterias> TiraMateriaActual = service.findById(idInsMateria);
			// En caso de no encontrar la tira de materia se regresa un not found
			if (!TiraMateriaActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el método para guardar el registro del repositorio JPA
			TiraMaterias TiraMateriaCreado = service.create(tiraMaterias);
			// Armamos la respuesta 
			String respuesta = TiraMateriaCreado.getIdInsMateria() + ": " + TiraMateriaCreado.getGrupo();
			return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
