package com.minsait.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.minsait.curso.model.entity.Prestamo;

/**
 * Representacion de prestamos, consulta los prestamos por numero de cuenta
 * @author Rey Castro
 *
 */
public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{
	
	/**
	 * Recupera la lista de prestamos por numero de cuenta del alumno
	 * @param numCuenta identificador del alumno
	 * @return la consulta del prestamo
	 */
	@Query(value="SELECT * FROM prestamo WHERE num_cuenta=:numCuenta ORDER BY fecha_salida ASC;", nativeQuery=true)
	public List<Prestamo> findByNumCuentaOrderByFechaSalidaAsc(Long numCuenta);

}
