package com.minsait.curso.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaController {

	@GetMapping("/bienvenida")
	
	public ResponseEntity<String> Bienvenida() {	
		return new ResponseEntity<String>("Bienvenido a la Biblioteca", HttpStatus.OK);
	}
}
