package com.minsait.curso.model.entity;

import java.sql.Date;

import lombok.Data;

/**
 * Representaci&#243;n de una inscripcion
 * @author 		Alejandro Vences
 * @version     1
 */

@Data
public class Inscripcion {
	
	/**
     * Representaci&#243;n del periodo de inscrpci&#243;n del alumno
     */
	private Long idPeriodo;
	
	 /**
     * Representaci&#243;n del n&#250;mero de cuenta o identificaci&#243;n del alumno
     */
	private Alumno numCuenta;
	
	/**
     * Representaci&#243;n de la fecha inicial del periodo de inscripci&#243;n
     */
	private Date fechaInicio;
	
	/**
     * Representaci&#243;n de la fecha final del periodo de inscripci&#243;n
     */
	private Date fechaFinal;
	 
	/**
     * Representaci&#243;n del estatus de la inscripci&#243;n: Pendiente, Finalizada o Baja
     */
	private String estatus;

}
