package com.minsait.curso.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.minsait.curso.model.entity.Inscripcion;

/**
 * 
 * @author Rey Castro
 *
 */
@FeignClient(value = "msvc-serviciosEscolaresIns", url="http://localhost:8082/api/servicios/escolares")
public interface InscripcionCliente {
	
	@GetMapping("/byNumCuenta/{numCuenta}")
	public ResponseEntity<Inscripcion> findByNumCuenta(@PathVariable Long numCuenta);

}
