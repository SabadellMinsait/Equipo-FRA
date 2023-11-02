package com.minsait.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.minsait.curso.model.entity.Inscripcion;



/**
 * Interfaz para generar las instrucciones dinamicas por JPA a la tabla de Inscripci&#243;n 
 * @author fvelez
 * @version 1.0
 */
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long>{

	/**
	 * Obtenermos la lista de Inscripciones de un alumno
	 * @param numCuenta: N&#250;mero de cuenta del alumno
	 * @return Lista de inscripciones de un alumno
	 */
	@Query(value="SELECT * FROM inscripcion WHERE num_cuenta=:numCuenta ORDER BY fecha_inicio DESC;", nativeQuery=true)
	public List<Inscripcion> findByNumCuentaOrderByFechaIngresoDesc(Long numCuenta);
}
