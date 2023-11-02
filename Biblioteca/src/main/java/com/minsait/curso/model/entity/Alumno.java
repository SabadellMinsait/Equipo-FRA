package com.minsait.curso.model.entity;

import java.sql.Date;
import lombok.Data;

/**
 * Representacion del etatus de un alumno
 * @author Rey Castro
 * Campos de alumno
 */
@Data
public class Alumno {
	
	private Long numCuenta;

	private String nombre;

	private String apellidoPat;

	private String apellidoMat;

	private String domicilio;

	private Date fechaIngreso;
	

}
