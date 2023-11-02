package com.minsait.curso.cliente; 

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.minsait.curso.model.entity.Alumno;

/**
 * Interfaz cliente para recuperar la informacion de alumno 
 * @author Rey Castro
 * FeignClient servicio cliente de alumnos
 */
@FeignClient(value = "msvc-serviciosEscolares", url="http://localhost:8082/api/servicios/escolares/alumnos")
public interface AlumnoCliente {
	
	/**
	 * Funcion para recuperar el estatus por numero de cuenta
	 * @param numCuenta identificador del alumno
	 * @return busqueda de numero de cuenta
	 * GetMapping asignar solicitudes HTTP GET a metodos de controlador
	 */
	@GetMapping("/{numCuenta}")
	public ResponseEntity<Alumno> findById(@PathVariable Long numCuenta);

}
