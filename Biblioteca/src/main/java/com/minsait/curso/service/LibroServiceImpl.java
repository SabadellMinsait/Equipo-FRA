package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.minsait.curso.model.entity.Libro;
import com.minsait.curso.repository.LibroRepository;

/**
 * Implementacion de LibroService para interactuar con el repositorio de Libro
 * @author Rey Castro
 *
 */
@Service
public class LibroServiceImpl implements LibroService{

	@Autowired
	LibroRepository repository;
	
	@Override
	public List<Libro> findAll() {
		return repository.findAll();
	}

	/**
	 * Recupera la lista general de los libros registrados en BD
	 */
	@Override
	public Optional<Libro> findById(Long idLibro) {
		return repository.findById(idLibro);
	}

	/**
	 * Crea un nuevo libro
	 */
	@Override
	public Libro create(Libro libro) {
		// Valida que el parámetro no se nulo
		Assert.notNull(libro, "El libro no puede ser nulo");
		// Se valida si ya existe
		if (libro.getIdLibro() != null) {
			Optional<Libro> libroActual = findById(libro.getIdLibro());
			Assert.isTrue(!libroActual.isPresent(), "El libro ya existe");
		}
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(libro);
	}

	/**
	 * Guarda un nuevo libro
	 */
	@Override
	public Libro save(Long idLibro, Libro libro) {
		// Aseguramos que el numero de cuenta sea el mismo
		libro.setIdLibro(idLibro);
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(libro);
	}

	/**
	 * Borra el registro de un libro
	 */
	@Override
	public void delete(Long idLibro) {
		// Se guarda el registro por el repositorio de JPA 
		repository.deleteById(idLibro);
	}
	
	/**
	 * Creacion de un LibroService vac&#237;o
	 */
	public LibroServiceImpl() {
		
	}

}
