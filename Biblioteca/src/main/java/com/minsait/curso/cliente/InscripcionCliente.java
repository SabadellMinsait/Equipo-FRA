package com.minsait.curso.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.minsait.curso.model.entity.Inscripcion;

/**
 * Interfaz cliente para recuperar la informacion de servicios escolares o incripcion por numero de cuenta 
 * @author Rey Castro
 * FeignClient servicio cliente de servicios escolares
 */
@FeignClient(value = "msvc-serviciosEscolaresIns", url="http://localhost:8082/api/servicios/escolares")
public interface InscripcionCliente {
	
	/**
	 * Funcion para recuperar el estatus por numero de cuenta
	 * @param numCuenta identificador del numero de cuenta
	 * @return Registro del status de servicios escolares
	 */
	@GetMapping("/byNumCuenta/{numCuenta}")
	public ResponseEntity<Inscripcion> findByNumCuenta(@PathVariable Long numCuenta);

}
