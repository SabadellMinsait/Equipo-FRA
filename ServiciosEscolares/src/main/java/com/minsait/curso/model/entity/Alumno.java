package com.minsait.curso.model.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 * Representaci&#243;n de un alumno
 * @author 		fvelez
 * @version     1
 */
@Entity
@Table (name = "alumno")
@Getter
@Setter
public class Alumno {

    /**
     * Representaci&#243;n del n&#250;mero de cuenta o identificaci&#243;n del alumno
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "num_cuenta")
	private Long numCuenta;
	
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
	
	/**
	 * Representaci&#243;n de la lista de inscripciones del alumno
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "num_cuenta")
	@JsonIgnore
	@Basic(optional=true)
	private List<Inscripcion> inscripciones;
	
	/**
	 * Creaci&#243;n de un alumno vac&#237;o
	 */	
	public Alumno() {
		
	}
	/**
	 * Convierte el alumno en formato JSon
	 */
	public String toString() {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		try {
			return  ow.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
}
