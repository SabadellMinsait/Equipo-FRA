package com.minsait.curso.model.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name = "alumno")
@Getter
@Setter
/**
 * Representaci&#243;n de un alumno
 * @author fvelez
 * @version 1.0
 */
public class Alumno {

    /**
     * Representaci&#243;n del numero de cuenta o identificaci&#243;n del alumno
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "num_cuenta")
	private Long num_cuenta;
	
    /**
     * Representaci&#243;n del nombre del alumno
     */
	@Column (name = "nombre")
	private String nombre;
    /**
     * Representaci&#243;n del apellid paterno del alumno
     */
	@Column (name = "apellido_pat")
	private String apellidoPat;
    /**
     * Representaci&#243;n del apellido materno del alumno
     */
	@Column (name = "apellido_mat")
	private String apellidoMat;
    /**
     * Representaci&#243;n del domicilio del alumno
     */
	@Column (name = "domiclio")
	private String domicilio;
    /**
     * Representaci&#243;n de la fecha de ingreso del alumno a la escuela
     */
	@Column (name = "fecha_ingreso")
	private Date fechaIngreso;
	
}
