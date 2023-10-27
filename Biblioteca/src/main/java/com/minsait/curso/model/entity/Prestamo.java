package com.minsait.curso.model.entity;

import java.util.Date;

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
 * Entity para interactuar Prestamos con Libros, Alumnos e Inscripcion
 * Table dedica a Prestamo
 * Getter obtine los identificadores y llaves foraneas
 * Setter coloca los valores agregados por usuario
 * @author Rey Castro
 *
 */
@Entity
@Table (name = "Prestamo")
@Getter
@Setter
public class Prestamo {
	
	/** Identificador del Prestamo**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "id_prestamo")
    private Long idPrestamo;
    
    /** Identificador del alumno**/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "numCuenta")
    private Long numCuenta;
    
    /** Identificador del periodo de inscripcion**/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name= "id_periodo")
    private Long idPeriodo;
    
    /** Identificador del libro**/
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_libro")
    @Basic(optional=false)
    private Libro idLibro;
    
    @Column (name="fecha_salida")
    private Date fechaSalida;
    
    @Column (name="fecha_entrega")
    private Date fechaEntrega;
    
    /** Observacion **/
    @Column (name="observacion")
    private String observacion;

}
