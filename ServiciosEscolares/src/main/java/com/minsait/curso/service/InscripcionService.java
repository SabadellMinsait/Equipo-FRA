package com.minsait.curso.service;

import java.util.List;

import com.minsait.curso.model.entity.Inscripcion;

/**
 * Interfaz para interactuar con el repositorio de Inscripci&#243;n
 * @author fvelez
 * @version 1.0
 */
public interface InscripcionService {
	
	/**
	 * Funci&#243;n para recuperar la lista completa de inscrpciones registrados 
	 * @return Regresa un List de la clase Inscrpcion
	 */
	public List<Inscripcion> findAll();
}
