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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.minsait.curso.model.entity.Prestamo;
import com.minsait.curso.service.PrestamoService;


/**
 * Representación para acceder al repositorio de Prestamos escolares
 * @author Rey Castro
 *
 */
@RestController
@RequestMapping("/api/biblioteca/prestamos")
public class PrestamoController {
	
	/**
	 * Servicio para acceder al repositorio de Prestamo
	 */
	@Autowired
	PrestamoService service;
	
	/**
	 * Devuelve una lista completa de los prestamos registrados
	 * @return lista de prestamos
	 */
	@GetMapping()
	public ResponseEntity<String> findAll() {	
		return new ResponseEntity<String>("callbackfn(" + service.findAll().toString() + ")", HttpStatus.OK);
	}
	
	/**
	 * Obtiene el prestamo seleccionado por el identificador
	 * @param idPrestamo identificador del prestamo
	 * @return Registro del prestamo
	 */
	@GetMapping("/{idPrestamo}")
	public ResponseEntity<Prestamo> findById(@PathVariable Long idPrestamo){
		Optional<Prestamo> prestamo = service.findById(idPrestamo);
		if (!prestamo.isPresent())
			return ResponseEntity.notFound().build();
		return new ResponseEntity<Prestamo>(prestamo.get(), HttpStatus.OK);
	}
	
	/**
	 * Guarda un nuevo registro de prestamo
	 * @param prestamo: Registro del Prestamo
	 * @return Registro del libro guardado
	 */
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	@Transactional
	public ResponseEntity<String> create(
    		@RequestBody Prestamo prestamo){
		try {
			// Agragamos el prestamo
			Prestamo prestamoCreado = service.create(prestamo);
			// Armamos la respuesta 
			String respuesta = prestamoCreado.getIdPrestamo() + ": " + prestamoCreado.getLibro().getIdLibro();
			// Se llama el método para crear el registro del repositorio JPA
			
		return new ResponseEntity<String>(respuesta, HttpStatus.CREATED);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Guarda un nuevo Prestamo
	 * @param idPrestamo Identificador del Prestamo
	 * @param prestamo Registro del prestamo
	 * @return registro del prestamo guardado
	 */
	@PutMapping("/{idPrestamo}")
	@Transactional
	public ResponseEntity<String> update(
    		@PathVariable Long idPrestamo,
			@RequestBody Prestamo prestamo){
		try {
			// Se valida si existe el identificador del libro
			Optional<Prestamo> prestamoActual = service.findById(idPrestamo);
			// En caso de no encontrar el libro se regresa un not found
			if (!prestamoActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el método para guardar el registro del repositorio JPA
			Prestamo prestamoCreado = service.save(idPrestamo, prestamo);
			// Armamos la respuesta 
			String respuesta = prestamoCreado.getIdPrestamo() + ": " + prestamoCreado.getLibro().getIdLibro();
			return new ResponseEntity<String>(respuesta, HttpStatus.OK);
		}catch(Exception e) {
			// Se muestra el detalle del error
			return new ResponseEntity<String>("0:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/**
	 * Borra el registro de un prestamo
	 * @param idPrestamo: identificador del prestamo
	 * @return Respuesta correcta o Excepcion en caso de haber algun error
	 */
	@DeleteMapping("/delete/{idPrestamo}")
	@ResponseStatus(HttpStatus.OK)
	@Transactional
    public ResponseEntity<?> delete(@PathVariable Long idPrestamo) {
		try {
			// Se valida si existe el identificador del prestamo
			Optional<Prestamo> prestamoActual = service.findById(idPrestamo);
			// En caso de que no se encontre se regresa un not found
			if (!prestamoActual.isPresent())
				return ResponseEntity.notFound().build();
			// Se llama el borrado del repositorio JPA
			service.delete(idPrestamo);
			// Regresamos el resultado como correcto
			return ResponseEntity.ok().build();
		}catch(Exception e) {
			return new ResponseEntity<Exception>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Creacion de un controlador de prestamo vacio 
	 */
	public PrestamoController() {
		
	}

	/**
	 * Funcion de busqueda por numero de cuenta
	 * @param numCuenta identificador de numero de cuenta
	 * @return la busqueda por numero de cuenta
	 */
	@GetMapping("/byNumCuenta/{numCuenta}")
	public List<Prestamo> findByNumCuenta(@PathVariable Long numCuenta) {	
		return service.findByNumCuenta(numCuenta);
	}
	
	/**
	 * Metodo que regresa el prestamos buscado
	 * @param numCuenta identificador del numero de cuenta del alumno
	 * @param idLibro identificador del libro
	 * @return la consulta del prestamos, si es existente
	 */
	@PutMapping("/returnBook")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> returnBook(@RequestParam Long numCuenta, @RequestParam Long idLibro) {
		
		String respuesta;
		try {			
			Optional<Prestamo> prestamo = service.returnBook(numCuenta, idLibro);
			
			if(prestamo.isPresent()) {
				respuesta = prestamo.get().getIdPrestamo() + ": " + prestamo.get().getLibro().getIdLibro();
			}else {
				respuesta = "0 : Prestamo no encontrado"; 
			}
			return new ResponseEntity<String>(respuesta, HttpStatus.OK); 
		}catch (Exception e) {
			return new ResponseEntity<String>("0:" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 
	}
}
