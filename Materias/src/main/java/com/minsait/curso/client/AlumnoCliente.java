package com.minsait.curso.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.minsait.curso.model.entity.Alumno;

/**
 * Interfaz cliente para recuperar la informaci&#243;n del alumno
 * @author Alejandro Vences
 */


@FeignClient(value = "msvc-serviciosEscolares", url="http://localhost:8082/api/servicios/escolares/alumnos")
public interface AlumnoCliente {
	
	/**
	 * Funci&#243;n para recuperar el estatus por n&#250;mero de cuenta
	 * @param numCuenta: N&#250;mero de cuenta del alumno
	 * @return Registro del status del alumno 
	 */
	
	@GetMapping("/{numCuenta}")
	public ResponseEntity<Alumno> findById(@PathVariable Long numCuenta);

}
