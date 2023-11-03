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
 * Representaci&#243;n de un profesor
 * @author 		alejandro vences
 * @version     1
 */

@Entity
@Table (name = "profesor")
@Getter
@Setter

public class Profesor {
	/** Identificador del profesor**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name= "id_profesor")
	private Long idProfesor;
	
	/** Nombre del profesor **/
	@Column (name="nombre")
	private String nombre;
	
	/** RFC del profesor **/
	@Column (name = "RFC")
	private String rfc;
	
	/** Especialidad del profesor **/
	@Column (name = "especialidad")
	private String especialidad;
	

	/**
	 * Representaci&#243;n de la lista de tiras de la materia asociadas
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_profesor")
	@JsonIgnore
	@Basic(optional=true)
	private List<TiraMaterias> tiraMateria;

	/**
	 * Convierte el registro de profesor en formato JSon
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
     * Funci&#243;n para generar un registro vacio de profesor
     */
	public Profesor() {
        
    }
}
