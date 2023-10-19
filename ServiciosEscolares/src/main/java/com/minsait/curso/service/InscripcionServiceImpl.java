package com.minsait.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minsait.curso.model.entity.Inscripcion;
import com.minsait.curso.repository.InscripcionRepository;

/**
 * Implementaci&#243;n de InscripcionService para interactuar con el repositorio de Inscripci&#243;n
 * @author fvelez
 * @version 1.0
 */
@Service
public class InscripcionServiceImpl implements InscripcionService{

	/**
	 * Propiedad repositorio de tipo InscrpcionRepository
	 */
	@Autowired
	InscripcionRepository repository;
	
	/**
	 * Funci&#243;n para recuperar la lista completa de inscrpciones registrados 
	 * @return Regresa un List de la clase Inscrpcion
	 */
	@Override
	public List<Inscripcion> findAll(){
		// Regresamos la lista genetada por la implementación a JPA
		return repository.findAll();
	}
}
