package com.minsait.curso.model.entity;

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
	
	/**
	 * Representaci&#243;n de la lista de tiras de la materia asociadas
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_materia")
	@JsonIgnore
	@Basic(optional=true)
	private List<TiraMaterias> tiraMateria;

	/**
	 * Convierte el materia en formato JSon
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

	/**
     * Funci&#243;n para generar un registro vacio de materia
     */
	public Materia() {
        
    }
}
