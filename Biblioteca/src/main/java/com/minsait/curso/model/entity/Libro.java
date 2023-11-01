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
 * Entity para interactuar con Biblioteca
 * Table nombrada para Libro
 * Getter obtiene los identificadores y campos
 * Setter Coloca los valores ingresado por usuario
 * @author Rey Castro
 *
 */
@Entity
@Table (name = "Libro")
@Getter
@Setter
public class Libro {
	
	/**
	 * Identificador de Libros
	 * Representacion numerica
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "id_libro")
    private Long idLibro;
    
    /** Titulo del libro **/
    @Column (name="titulo")
    private String titulo;
    
    /** Breve resumen del libro **/
    @Column (name = "resumen")
    private String resumen;
    
    /** Numeros disponibles **/
    @Column (name = "disponibles")
    private Integer disponibles;
    
    /**
     * Relación con la tabla de Prestamo
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_libro")
    @JsonIgnore
    @Basic(optional=true)
    private List<Prestamo> prestamo;

	/**
	 * Convierte el libro en formato JSon
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
