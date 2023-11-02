package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import com.minsait.curso.model.entity.Prestamo;


/**
 * Servicio de Prestamos, contiene los metodos ABC para el flujo de prestamos
 * @author Rey Castro
 *
 */
public interface PrestamoService {
	
	/**
	 * Funcion de busqueda general del prestamo
	 * @return el registro de los prestamos
	 */
	public List<Prestamo> findAll();
	
	/**
	 * Funcion para recuperar el detalle del Prestamo por identificador
	 * @param idPrestamo: Identificador del Prestamo
	 * @return Registro del Prestamo
	 */
	public Optional<Prestamo> findById(Long idPrestamo);
	
	/**
	 * Funcion para guardar un nuevo Prestamo 
	 * @param prestamo: Registro del Prestamo
	 * @return Registro del Prestamo guardado
	 */
	public Prestamo create(Prestamo prestamo);
	
	/**
	 * Funcion para guardar un Prestamo 
	 * @param idPrestamo: Identificador del Prestamo
	 * @param prestamo: Registro del Prestamo a guardar
	 * @return Registro del Prestamo guardado
	 */
	public Prestamo save(Long idPrestamo, Prestamo prestamo);
	
	/**
	 * Funcion para borrar un Prestamo
	 * @param idPrestamo: Identificador del Prestamo
	 */
	public void delete(Long idPrestamo);
	
	/**
	 * 
	 * @param numCuenta identificador del alumno
	 * @return la busqueda completa
	 */
	public List<Prestamo> findByNumCuenta(Long numCuenta);
	
	/**
	 * Funcion para retornar los datos del libro
	 * @param numCuenta identificador del alumno
	 * @param idLibro identificador del libro
	 * @return el prestamo
	 */
	public Optional<Prestamo> returnBook(Long numCuenta, Long idLibro);

}
