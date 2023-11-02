package com.minsait.curso.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.minsait.curso.model.entity.StatusBiblioteca;

/**
 * Interfaz cliente para recuperar la informaci&#243;n de la biblioteca
 * @author fvelez
 * @version 1.0
 */

@FeignClient(value = "msvc-statusBiblioteca", url="http://localhost:8083/api/biblioteca")
public interface StatusBibliotecaClient {

	/**
	 * Funci&#243;n para recuperar el estatus por n&#250;mero de cuenta
	 * @param numCuenta: N&#250;mero de cuenta del alumno
	 * @return Registro del status del alumno en la biblioteca
	 */
	@GetMapping("/status/{numCuenta}")
	public ResponseEntity<StatusBiblioteca> findByNumcuenta(@PathVariable Long numCuenta);
}
