package com.minsait.curso.model.entity;

import java.sql.Date;
import lombok.Data;

@Data
public class Alumno {
	
	private Long numCuenta;

	private String nombre;

	private String apellidoPat;

	private String apellidoMat;

	private String domicilio;

	private Date fechaIngreso;
	

}
