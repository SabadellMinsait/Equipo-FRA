package com.minsait.curso.model.entity;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 * Representaci&#243;n de una tiraMateria
 * @author 		Alejandro Vences
 * @version     1
 */

@Entity
@Table (name = "Tira_materia")
@Getter
@Setter


public class TiraMaterias {

	/** Identificador de la tira de materias**/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name= "id_ins_materia")
	private Long idInsMateria;
	
	/** Identificador del alumno**/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name= "num_cuenta")
	private Long numCuenta;
	
	/** Identificador del profesor**/
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profesor")
	@Basic(optional=false)
	private Profesor profesor;
	
	/** Identificador del periodo de inscripcion**/
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name= "id_periodo")
	private Long idPeriodo;
	
	/** Identificador de la materia**/
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_materia")
	@Basic(optional=false)
	private Materia materia;
	
	
	/** grupo al que esta asignado**/
	@Column (name="grupo")
	private String grupo;
	
	/** Hora inicial de la materia **/
	@Column (name = "hora_inicio")
	private String horaInicio;
	
	/** Hora en que termina la materia **/
	@Column (name = "hora_fin")
	private String horaFin;
	
	/** Dia en que toca la materia **/
	@Column (name = "dia_semana")
	private String diaSemana;

	/**
	 * Convierte la tira de paterias en formato JSon
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
     * Funci&#243;n para generar un registro vacio de TiraMaterias
     */
	public TiraMaterias() {
        
    }	

}
