package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import com.minsait.curso.model.entity.Prestamo;


/**
 * 
 * @author Rey Castro
 *
 */
public interface PrestamoService {
	
	public List<Prestamo> findAll();
	
	/**
	 * Funcion para recuperar el detalle del Prestamo por identificador
	 * @param idPrestamo: Identificador del Prestamo
	 * @return Registro del Prestamo
	 */
	public Optional<Prestamo> findById(Long idPrestamo);
	
	/**
	 * Funcion para guardar un nuevo Prestamo 
	 * @param Libro: Registro del Prestamo
	 * @return Registro del Prestamo guardado
	 */
	public Prestamo create(Prestamo prestamo);
	
	/**
	 * Funcion para guardar un Prestamo 
	 * @param idPrestamo: Identificador del Prestamo
	 * @param Prestamo: Registro del Prestamo a guardar
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
	 * @param numCuenta
	 * @return
	 */
	public List<Prestamo> findByNumCuenta(Long numCuenta);
	
	/**
	 * 
	 * @param numCuenta
	 * @param idLibro
	 */
	public void retunBook(Long numCuenta, Long idLibro);

}
