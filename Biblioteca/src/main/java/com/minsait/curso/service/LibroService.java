package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import com.minsait.curso.model.entity.Libro;

/**
 * Interfaz para interactuar con el repositorio de Libro
 * @author Rey Castro
 *
 */
public interface LibroService {

	public List<Libro> findAll();
	
	/**
	 * Funcion para recuperar el detalle del Libro por identificador
	 * @param idLibro: Identificador del Libro
	 * @return Registro del Libro
	 */
	public Optional<Libro> findById(Long idLibro);
	
	/**
	 * Funcion para guardar un nuevo Libro 
	 * @param Libro: Registro del Libro
	 * @return Registro del Libro guardado
	 */
	public Libro create(Libro libro);
	
	/**
	 * Funcion para guardar un Libro 
	 * @param idLibro: Identificador del Libro
	 * @param Libro: Registro del Libro a guardar
	 * @return Registro del Libro guardado
	 */
	public Libro save(Long idLibro, Libro libro);
	
	/**
	 * Funcion para borrar un Libro
	 * @param idLibro: Identificador del Libro
	 */
	public void delete(Long idLibro);
}
