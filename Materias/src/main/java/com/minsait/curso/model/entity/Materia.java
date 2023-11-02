package com.minsait.curso.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
 * Representaci&#243;n de la inscrpci&#243;n anual de un alumno a la escuela
 * @author fvelez
 * @version 1.0
 */

@Entity
@Table (name = "materia")
@Getter
@Setter


public class Materia {
	
	/**
     * Representaci&#243;n del identificador de la materia
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name= "id_materia")
	private Long idMateria;
	
	/**
     * Representaci&#243;n del nombre de la materia
     */
	@Column (name="nombre")
	private String nombre;
	
	/**
     * Representaci&#243;n del resumen de la materia
     */
	@Column (name = "resumen")
	private String resumen;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_materia")
	@JsonIgnore
	@Basic(optional=true)

	private List<TiraMaterias> tiraMateria;
}
