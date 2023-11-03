package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.minsait.curso.model.entity.Profesor;
import com.minsait.curso.repository.ProfesorRepository;


/**
 * Implementaci&#243;n de ProfesorService para interactuar con el repositorio de Profesor
 * @author Alejandro Vences
 * @version 1.0
 */
@Service
public class ProfesorServiceImpl implements ProfesorService{

	/**
	 * Propiedad repositorio de tipo ProfesorRepository
	 */
	@Autowired
	private ProfesorRepository repository;
	
	/**
	 * Funci&#243;n para recuperar la lista completa de profesores registrados 
	 * @return Regresa un List de la clase Profesor
	 */
	@Override
	public List<Profesor> findAll() {
		// Regresamos la lista genetada por la implementación a JPA
		return repository.findAll();
	}

	/**
	 * Funci&#243;n para recuperar el detalle del profesor por id del profesor
	 * @param idProfesor: Identificador del profesor
	 * 	 * @return Registro del profesor
	 */
	@Override
	public Optional<Profesor> findById(Long idProfesor) {
		// Buscamos el profesor por su identificador por el repositorio de JPA 
		return repository.findById(idProfesor);
	}

	/**
	 * Funci&#243;n para guardar un nuevo profesor 
	 * @param profesor: Registro del profesor
	 * @return Registro del profesor guardado
	 */
	@Override
	public Profesor create(Profesor profesor) {
		// Valida que el parámetro no se nulo
		Assert.notNull(profesor, "El profesor no puede ser nulo");
		validaProfesor(profesor);
		// Se valida si cuenta ya con numero de cuenta
		if (profesor.getIdProfesor() != null) {
			Optional<Profesor> alumnoActual = findById(profesor.getIdProfesor());
			Assert.isTrue(!alumnoActual.isPresent(), "El numero de identificacion ya existe");
		}
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(profesor);
	}

	/**
	 * Funci&#243;n para guardar un profesor 
	 * @param idProfesor: N&#250;mero de identificacion de un profesor
	 * @param profesor: Registro del profesor a guardar
	 * @return Registro del profesor guardado
	 */
	@Override
	public Profesor save(Long idProfesor, Profesor profesor) {
		validaProfesor(profesor);
		// Aseguramos que el numero de identificacion sea el mismo
		profesor.setIdProfesor(idProfesor);
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(profesor);
	}

	/**
	 * Funci&#243;n para borrar un profesor
	 * @param idProfesor identificador del profesor
	 */
	@Override
	public void delete(Long idProfesor) {
		// Se guarda el registro por el repositorio de JPA 
		repository.deleteById(idProfesor);
		
	}

	/**
	 * Validaci&#243;n que el profesor sea correcta
	 * @param profesor Registro del profesor
	 */
	public void validaProfesor(Profesor profesor) {
		Assert.isTrue(profesor.getNombre() !=null && !profesor.getNombre().equals(""), "El nombre del profesor no puede estar vacio");
		Assert.isTrue(profesor.getEspecialidad() !=null && !profesor.getEspecialidad().equals(""), "La especialidad del profesor no puede estar vacio");
		Assert.isTrue(profesor.getRfc() !=null && !profesor.getRfc().equals(""), "El RFC del profesor no puede estar vacio");
		
	}

	/**
     * Funci&#243;n para generar un registro vacio de profesor services implementation
     */
	public ProfesorServiceImpl() {
        
    }
}
