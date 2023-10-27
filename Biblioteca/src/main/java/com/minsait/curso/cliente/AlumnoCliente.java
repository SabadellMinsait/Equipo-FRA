package com.minsait.curso.cliente;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.minsait.curso.model.entity.Alumno;

/**
 * 
 * @author Rey Castro
 *
 */
@FeignClient(value = "msvc-serviciosEscolares", url="http://localhost:8082/api/servicios/escolares/alumnos")
public interface AlumnoCliente {
	
	@GetMapping("/{numCuenta}")
	public ResponseEntity<Alumno> findById(@PathVariable Long numCuenta);

}
