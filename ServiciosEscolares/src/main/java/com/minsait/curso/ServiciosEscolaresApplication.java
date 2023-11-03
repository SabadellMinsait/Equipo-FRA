package com.minsait.curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Representaci&#243;n de la aplicaci&#243;n principal de Servicios Escolares
 * @author fvelez
 * @version 1.0
 */
@EnableFeignClients
@SpringBootApplication
public class ServiciosEscolaresApplication {
	/**
	 * M&#233;todo principal de la aplicaci&#243;n de Servicios Escolares  
	 * @param args Argumentos generales de la aplicaci&#243;n
	 */
	public static void main(String[] args) {
		SpringApplication.run(ServiciosEscolaresApplication.class, args);
	}
	
	/**
	 * Creaci&#243;n de ua aplicaci&#243;n de servicios escolares vac&#237;o
	 */
	public ServiciosEscolaresApplication() {
		
	}

}
