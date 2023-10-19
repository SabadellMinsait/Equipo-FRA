package com.minsait.curso.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Representaci&#243;n del controlador principal de Servicios Escolares
 * @author fvelez
 * @version 1.0
 */
@RestController
@RequestMapping("/api/servicios/escolares")
public class ServiciosEscolaresController {
	
	/**
	 * End point de bienvenida
	 * @return 
	 */
	@GetMapping("/bienvenida")
	public ResponseEntity<String> Bienvenida() {	
		return new ResponseEntity<String>("Bienvenido a servicios escolares", HttpStatus.OK);
	}
}
