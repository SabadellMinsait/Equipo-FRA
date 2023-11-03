package com.minsait.curso.service;

import java.util.List;
import java.util.Optional;

import com.minsait.curso.model.entity.Materia;
import com.minsait.curso.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Implementaci&#243;n de MateriaService para interactuar con el repositorio de Materia
 * @author Alejandro Vences
 * @version 1.0
 */

@Service

public class MateriaServiceImpl implements MateriaService {
	
	/**
	 * Propiedad repositorio de tipo MateriaRepository
	 */
	@Autowired
	private MateriaRepository repository;
	
	/**
	 * Funci&#243;n para recuperar la lista completa de materias registrados 
	 * @return Regresa un List de la clase Materia
	 */
	@Override
	public List<Materia> findAll() {
		// Regresamos la lista generada por la implementación a JPA
		return repository.findAll();
	}

	/**
	 * Funci&#243;n para recuperar el detalle de las materias por identificador
	 * @param idMateria: Identificador de la materia
	 * @return Registro de la materia
	 */
	@Override
	public Optional<Materia> findById(Long idMateria) {
		// Buscamos la materia por identificador por el repositorio de JPA 
		return repository.findById(idMateria);
	}

	/**
	 * Funci&#243;n para guardar una nueva materia 
	 * @param materia: Registro de la materia
	 * @return Registro de la materia guardada
	 */
	@Override
	public Materia create(Materia materia) {
		// Valida que el parámetro no se nulo
		Assert.notNull(materia, "La materia no puede ser nula");
		validaMateria(materia);
		// Se valida si cuenta ya con numero de cuenta
		if (materia.getIdMateria() != null) {
			Optional<Materia> materiaActual = findById(materia.getIdMateria() );
			Assert.isTrue(!materiaActual.isPresent(), "El numero de materia ya existe");
		}
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(materia);
	}

	/**
	 * Funci&#243;n para guardar una materia 
	 * @param idMateria: Identificador de la materia
	 * @param materia: Registro de la materia
	 * @return materia del alumno guardado
	 */
	@Override
	public Materia save(Long idMateria, Materia materia) {
		
		validaMateria(materia);
		// Aseguramos que el numero de cuenta sea el mismo
		materia.setIdMateria(idMateria);
		// Se guarda el registro por el repositorio de JPA 
		return repository.save(materia);
	}

	/**
	 * Funci&#243;n para borrar un alumno
	 * @param idMateria Identificador de la materia
	 */
	@Override
	public void delete(Long idMateria) {
		// Se guarda el registro por el repositorio de JPA 
		repository.deleteById(idMateria);
		
	}
	/**
	 * Funci&#243;n para validar la materia este correcta
	 * @param materia Registro de materia
	 */
	public void validaMateria(Materia materia) {
		Assert.isTrue(materia.getNombre() !=null && !materia.getNombre().equals(""), "El nombre de la materia no puede estar vacio");
		Assert.isTrue(materia.getResumen() !=null && !materia.getResumen().equals(""), "El resumen de la materia no puede estar vacio");
	}

	/**
     * Funci&#243;n para generar un registro vacio de Materia Service Impl
     */
	public MateriaServiceImpl() {
        
    }
}
