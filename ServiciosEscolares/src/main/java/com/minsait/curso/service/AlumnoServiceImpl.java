package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.minsait.curso.model.entity.Alumno;
import com.minsait.curso.repository.AlumnoRepository;

/**
 * Implementaci&#243;n de AlumnoService para interactuar con el repositorio de Alumno
 * @author fvelez
 * @version 1.0
 */
@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	/**
	 * Propiedad repositorio de tipo AlumnoRepository
	 */
	@Autowired
	private AlumnoRepository repository;
	
	/**
	 * Funci&#243;n para recuperar la lista completa de alumnos registrados 
	 * @return Regresa un List de la clase Alumnos
	 */
	@Override
	public List<Alumno> findAll() {
		// Regresamos la lista genetada por la implementación a JPA
		return repository.findAll();
	}

	/**
	 * Funci&#243;n para recuperar el detalle del alumno por numero de cuenta
	 * @param numCuenta: N&#250;mero de cuenta del alumno
	 * @return Registro del alumno
	 */
	@Override
	public Optional<Alumno> findById(Long numCuenta) {
		// Buscamos el alumno por numero de cuenta por el repositorio de JPA 
		return repository.findById(numCuenta);
	}

	/**
	 * Funci&#243;n para guardar un nuevo alumno 
	 * @param alumno: Registro del alumno
	 * @return Registro del alumno guardado
	 */
	@Override
	public Alumno create(Alumno alumno) {
		// Valida que el parámetro no se nulo
		Assert.notNull(alumno, "El alumno no puede ser nulo");
		// Se valida si cuenta ya con numero de cuenta
		if (alumno.getNumCuenta() != null) {
			Optional<Alumno> alumnoActual = findById(alumno.getNumCuenta());
			Assert.isTrue(!alumnoActual.isPresent(), "El numero de cuenta ya existe");
		}
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(alumno);
	}

	/**
	 * Funci&#243;n para guardar un alumno 
	 * @param alumno: Registro del alumno
	 * @return Registro del alumno guardado
	 */
	@Override
	public Alumno save(Long numCuenta, Alumno alumno) {
		// Aseguramos que el numero de cuenta sea el mismo
		alumno.setNumCuenta(numCuenta);
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(alumno);
	}

	/**
	 * Funci&#243;n para borrar un alumno
	 * @param numCuenta Numero de cuenta del alumno
	 */
	@Override
	public void delete(Long numCuenta) {
		// Se guarda el registro por el repositorio de JPA 
		repository.deleteById(numCuenta);
		
	}


}
