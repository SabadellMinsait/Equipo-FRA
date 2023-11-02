package com.minsait.curso.model.entity;

import java.sql.Date;

import lombok.Data;

/**
 * Representacion del etatus de inscripcion
 * @author Rey Castro
 *
 */
@Data
public class Inscripcion {
	
	private Long idPeriodo;

	private Alumno numCuenta;
	
	private Date fechaInicio;
	
	private Date fechaFinal;

	private String estatus;

}
