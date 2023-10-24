package com.minsait.curso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Representaci&#243;n del controlador principal para los materias
 * @author fvelez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/materias")
public class MateriasController {

	/**
	 * End point de bienvenida
	 * @return Regresa un saludo a la API de alumnos
	 */
	@GetMapping("/bienvenida")
	public String Bienvenida() {	
		return "Bienvenido a materias";
	}

	/**
	 * Creaci&#243;n de un controlador de materias vac&#237;o 
	 */
	public MateriasController() {
		
	}
}
