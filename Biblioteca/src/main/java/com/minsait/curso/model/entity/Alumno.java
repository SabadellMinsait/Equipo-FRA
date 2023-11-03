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
	 /**
     * Representaci&#243;n del n&#250;mero de cuenta o identificaci&#243;n del alumno
     */
	private Long numCuenta;
	
	/**
     * Representaci&#243;n del nombre del alumno
     */
	private String nombre;
	
	/**
     * Representaci&#243;n del apellid paterno del alumno
     */
	private String apellidoPat;

	/**
     * Representaci&#243;n del apellido materno del alumno
     */
	private String apellidoMat;
	/**
     * Representaci&#243;n de la fecha de ingreso del alumno a la escuela
     */
	private Date fechaIngreso;

	/**
     * Funci&#243;n para generar un registro vacio de alumno
     */
	public Alumno() {
        
    }

}
