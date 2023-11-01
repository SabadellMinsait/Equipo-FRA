package com.minsait.curso.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.minsait.curso.model.entity.Prestamo;

/**
 * 
 * @author Rey Castro
 *
 */
public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{
	
	/**
	 * 
	 * @param numCuenta
	 * @return
	 */
	@Query(value="SELECT * FROM prestamo WHERE num_cuenta=:numCuenta ORDER BY fecha_salida ASC;", nativeQuery=true)
	public List<Prestamo> findByNumCuentaOrderByFechaSalidaAsc(Long numCuenta);

}
