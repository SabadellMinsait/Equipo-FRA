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

@Entity
@Table (name = "inscripcion")
@Getter
@Setter
/**
 * Representaci&#243;n de la inscrpcion anual de un alumno a la escuela
 * @author fvelez
 * @version 1.0
 */
public class Inscripcion {

    /**
     * Representaci&#243;n del periodo de inscrpcion del alumno
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_periodo")
	private long id_periodo;

    /**
     * Representaci&#243;n del numero de cuenta o identificaci&#243;n del alumno
     */
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "num_cuenta")
	@Basic(optional=true)
	@Column (name = "num_cuenta")
	private Long num_cuenta;
	
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
     * Representaci&#243;n del estatus de la inscripción: Pendiente, Finalizada o Baja
     */
	@Column (name = "estatus")
	private String estatus;
}
