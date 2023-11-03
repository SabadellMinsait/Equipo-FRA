package com.minsait.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Representaci&#243;n de la aplicaci&#243;n principal de Materias
 * @author fvelez
 * @version 1.0
 */
@EnableFeignClients
@SpringBootApplication
public class MateriasApplication {

	/**
	 * M&#233;todo principal de la aplicaci&#243;n de Servicios Escolares  
	 * @param args Argumentos generales de la aplicaci&#243;n
	 */
	public static void main(String[] args) {
		SpringApplication.run(MateriasApplication.class, args);
	}
	
	/**
	 * Creaci&#243;n de ua aplicaci&#243;n de materias vac&#237;a
	 */
	public MateriasApplication() {
		
	}

}
