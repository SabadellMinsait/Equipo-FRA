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

import com.minsait.curso.model.entity.Libro;
import com.minsait.curso.service.LibroService;

/**
 * Representación para acceder al repositorio de Libro
 * @author Rey Castro
 *
 */
@RestController
@RequestMapping("/api/biblioteca/libros")
public class LibroController {

	/**
	 * Servicio para acceder al repositorio de Libros
	 */
	@Autowired
	LibroService service;
	
	/**
	 * Devuelve una lista completa de los libros registrados
	 * @return lista de libros
	 */
	@GetMapping()
	public ResponseEntity<String> findAll() {	
		return new ResponseEntity<String>("callbackfn(" + service.findAll().toString() + ")", HttpStatus.OK);
	}
	
	/**
	 * Obtiene el libro seleccionado por el identificador
	 * @param idLibro identificador del Libro
	 * @return Registro del libro
	 */
	@GetMapping("/{idLibro}")
	public ResponseEntity<Libro> findById(@PathVariable Long idLibro){
		Optional<Libro> libro = service.findById(idLibro);
		if (!libro.isPresent())
			return ResponseEntity.notFound().build();
		return new ResponseEntity<Libro>(libro.get(), HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo registro de libro
	 * @param libro: Registro del libro
	 * @return Registro del libro guardado
	 */
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public ResponseEntity<String> create(
    		@RequestBody Libro libro){
		try {
			// Agragamos el libro
			Libro libroCreado = service.create(libro);
			// Armamos la respuesta 
			String respuesta = libroCreado.getIdLibro() + ": " + libroCreado.getTitulo();
			// Se llama el método para crear el registro del repositorio JPA
			
		return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Guarda un nuevo libro
	 * @param idLibro Identificador del libro
	 * @param libro Registro del libro
	 * @return registro del libro guardado
	 */
	@PutMapping("/{idLibro}")
	@Transactional
	public ResponseEntity<String> update(
    		@PathVariable Long idLibro,
			@RequestBody Libro libro){
		try {
			// Se valida si existe el identificador del libro
			Optional<Libro> libroActual = service.findById(idLibro);
			// En caso de no encontrar el libro se regresa un not found
			if (!libroActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el método para guardar el registro del repositorio JPA
			Libro libroCreado = service.save(idLibro, libro);
			// Armamos la respuesta 
			String respuesta = libroCreado.getIdLibro() + ": " + libroCreado.getTitulo();
			return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Borra el registro de un libro
	 * @param idLibro: identificador del libro
	 * @return Respuesta correcta o Excepcion en caso de haber algun error
	 */
	@DeleteMapping("/delete/{idLibro}")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
    public ResponseEntity<?> delete(@PathVariable Long idLibro) {
		try {
			// Se valida si existe el identificador del libro
			Optional<Libro> libroActual = service.findById(idLibro);
			// En caso de que no se encontre se regresa un not found
			if (!libroActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el borrado del repositorio JPA
			service.delete(idLibro);
			// Regresamos el resultado como correcto
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Creacion de un controlador de libro vacio 
	 */
	public LibroController() {
		
	}
}
