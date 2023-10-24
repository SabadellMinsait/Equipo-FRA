package com.minsait.curso.model.entity;

import java.sql.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Representaci&#243;n de la inscrpci&#243;n anual de un alumno a la escuela
 * @author fvelez
 * @version 1.0
 */
@Entity
@Table (name = "inscripcion")
@Getter
@Setter
public class Inscripcion {

    /**
     * Representaci&#243;n del periodo de inscrpci&#243;n del alumno
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_periodo")
	private Long idPeriodo;

    /**
     * Representaci&#243;n del n&#250;mero de cuenta o identificaci&#243;n del alumno
     */
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "num_cuenta")
	@Basic(optional=false)
	private Alumno alumno;
	
    /**
     * Representaci&#243;n de la fecha inicial del periodo de inscripci&#243;n
     */
	@Column (name = "fecha_inicio")
	private Date FechaInicio;

    /**
     * Representaci&#243;n de la fecha final del periodo de inscripci&#243;n
     */
	@Column (name = "fecha_final")
	private Date FechaFinal;
	
    /**
     * Representaci&#243;n del estatus de la inscripci&#243;n: Pendiente, Finalizada o Baja
     */
	@Column (name = "estatus")
	private String estatus;
	
	/**
	 * Creaci&#243;n de una inscripci&#243;n vac&#237;a
	 */
	public Inscripcion() {
		
	}
	
}
