package com.minsait.curso.model.entity;
 
import lombok.Data;
 
/**
 * Representaci&#243;n del etatus de un alumno
 * @author fvelez
 * @version 1.0
 */
@Data
public class Status {

	/**
	 * N&#250;mero de cuenta del alumno consultado 
	 */
	Long numCuenta;
	
	/**
	 * Descripci&#243;n del estatus del alumno
	 */
	String status; 
	
	/**
	 * Creaci&#243;n de una estructura de status vac&#237;o
	 */
	public Status() {
		
	}
	
}
