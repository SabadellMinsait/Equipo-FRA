package com.minsait.curso.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/materias")
public class MateriasController {

	@GetMapping("/bienvenida")
	
	public String Bienvenida() {	
		return "Bienvenido a materias";
	}
}
