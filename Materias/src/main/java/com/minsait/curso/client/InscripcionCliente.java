package com.minsait.curso.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.minsait.curso.model.entity.Inscripcion;

/**
 * 
 * Interfaz cliente para recuperar la informaci&#243;n del alumno
 * @author Alejandro Vences
 *
 */
@FeignClient(value = "msvc-serviciosEscolaresIns", url="http://localhost:8082/api/servicios/escolares")
public interface InscripcionCliente {
	
	/**
	 * Funci&#243;n para recuperar el estatus por n&#250;mero de cuenta
	 * @param numCuenta: N&#250;mero de cuenta del alumno
	 * @return Registro del status del alumno en una inscripci&#243;n
	 */
	
	@GetMapping("/byNumCuenta/{numCuenta}")
	public ResponseEntity<Inscripcion> findByNumCuenta(@PathVariable Long numCuenta);

}
